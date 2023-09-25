package com.example.uploadfilebinary.service;

import com.example.uploadfilebinary.model.FileEntity;
import com.example.uploadfilebinary.repository.IFileEntityRepository;
import com.example.uploadfilebinary.storage.FirebaseConfig;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class FileEntityService implements IFileEntityService {
    //    @Value("${app.firebase-bucket}")
//    private String bucket;
    IFileEntityRepository entityRepository;
    FirebaseConfig firebaseConfig;

    @Autowired
    public FileEntityService(IFileEntityRepository entityRepository, FirebaseConfig firebaseConfig) {
        this.entityRepository = entityRepository;
        this.firebaseConfig = firebaseConfig;
    }

    @Override
    public boolean save(MultipartFile file) {
        try {
            Bucket bucket = StorageClient.getInstance().bucket();
            byte[] fileBytes = file.getBytes();
            Blob blob = bucket.create(file.getOriginalFilename(), fileBytes, file.getContentType());
            String url = "https://firebasestorage.googleapis.com/v0/b/" + bucket.getName() + "/o/" + file.getOriginalFilename();

            System.out.println(blob.asBlobInfo());
            FileEntity fileEntity = new FileEntity();
            fileEntity.setFileName(file.getOriginalFilename());
            fileEntity.setFileType(file.getContentType());
            fileEntity.setUrl(url);
            entityRepository.save(fileEntity);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<FileEntity> findById(Long id) {
        return entityRepository.findById(id);
    }
}
