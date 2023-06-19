package com.example.uploadfilebinary.controller;

import com.example.uploadfilebinary.model.FileEntity;
import com.example.uploadfilebinary.repository.FileEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/home")
public class FileController {
    @Autowired
    private FileEntityRepository fileRepository;

    @GetMapping("")
    public ModelAndView showHome() {
        ModelAndView modelAndView = new ModelAndView("/test-file");
        List<FileEntity> fileOptional = fileRepository.findAll();
        if (!fileOptional.isEmpty()) {
            modelAndView.addObject("files",fileOptional);
        }
        return modelAndView;
    }


}
