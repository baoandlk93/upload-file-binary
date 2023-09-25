package com.example.uploadfilebinary.service;

import com.example.uploadfilebinary.model.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface IFileEntityService {
    boolean save(MultipartFile file);

   Optional<FileEntity>  findById(Long id);

}
