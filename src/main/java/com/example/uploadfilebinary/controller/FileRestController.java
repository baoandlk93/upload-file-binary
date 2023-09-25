package com.example.uploadfilebinary.controller;

import com.example.uploadfilebinary.model.FileEntity;
import com.example.uploadfilebinary.service.IFileEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/files")
public class FileRestController {

    private final IFileEntityService fileEntityService;

    @Autowired
    public FileRestController(IFileEntityService fileEntityService) {
        this.fileEntityService = fileEntityService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestBody MultipartFile file) {
        boolean check = fileEntityService.save(file);
        if (check) {
            return ResponseEntity.ok("File uploaded successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileEntity> getFile(@PathVariable Long id) {
        Optional<FileEntity> fileOptional = fileEntityService.findById(id);
        if (fileOptional.isPresent()) {
            FileEntity fileEntity = fileOptional.get();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(fileEntity.getFileType()));
            headers.setContentDisposition(ContentDisposition.builder("inline")
                    .filename(fileEntity.getFileName()).build());

            return new ResponseEntity<>(fileEntity, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}