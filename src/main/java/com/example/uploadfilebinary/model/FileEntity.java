package com.example.uploadfilebinary.model;

import lombok.*;

import javax.persistence.*;


@Table(name = "file_entity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String fileType;

//    @Lob
//    private byte[] data;
    private String url;

}
