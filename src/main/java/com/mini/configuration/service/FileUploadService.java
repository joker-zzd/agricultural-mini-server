package com.mini.configuration.service;

import com.mini.resultvo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
    ResultVO<String> upload(MultipartFile file) throws IOException;
}
