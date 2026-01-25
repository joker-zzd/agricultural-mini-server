package com.mini.configuration.controller;

import com.mini.configuration.service.FileUploadService;
import com.mini.resultvo.ResultVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/api/file")
public class FileUploadController {
    private final FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/upload")
    public ResultVO<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        return this.fileUploadService.upload(file);
    }

}
