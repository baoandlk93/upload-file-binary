package com.example.uploadfilebinary.repository;

import com.example.uploadfilebinary.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileEntityRepository extends JpaRepository<FileEntity, Long> {
    Optional<FileEntity> findByFileName(String name);
}
