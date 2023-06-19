package com.example.uploadfilebinary.service;

import com.example.uploadfilebinary.model.FileEntity;
import com.example.uploadfilebinary.repository.FileEntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileEntityService {
    FileEntityRepository entityRepository;

    public FileEntityService(FileEntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    public FileEntity store(MultipartFile file) {
        String fileName = "";
        FileEntity entity = new FileEntity();
        return entity;
    }
}
