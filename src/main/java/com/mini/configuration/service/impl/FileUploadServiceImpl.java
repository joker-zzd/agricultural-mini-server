package com.mini.configuration.service.impl;

import com.mini.configuration.AlipayConfiguration;
import com.mini.configuration.TencentCosProperties;
import com.mini.configuration.service.FileUploadService;
import com.mini.resultvo.ResultVO;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.region.Region;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    private final TencentCosProperties cosProperties;


    public FileUploadServiceImpl(TencentCosProperties cosProperties) {
        this.cosProperties = cosProperties;
    }

    @Override
    public ResultVO<String> upload(MultipartFile file) throws IOException {
        COSCredentials credentials = new BasicCOSCredentials(cosProperties.getSecretId(), cosProperties.getSecretKey());
        ClientConfig clientConfig = new ClientConfig(new Region(cosProperties.getRegion()));
        COSClient cosClient = new COSClient(credentials, clientConfig);

        String originalName = Objects.requireNonNull(file.getOriginalFilename());
        String suffix = originalName.substring(originalName.lastIndexOf("."));
        String fileName = UUID.randomUUID() + suffix;
        String path = cosProperties.getPrefix() + LocalDate.now() + "/" + fileName;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());

        cosClient.putObject(cosProperties.getBucketName(), path, file.getInputStream(), metadata);
        cosClient.shutdown();

        return ResultVO.url(cosProperties.getUrlPrefix() + path);
    }
}
