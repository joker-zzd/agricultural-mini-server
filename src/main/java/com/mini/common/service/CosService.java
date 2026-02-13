package com.mini.common.service;

import com.mini.common.domain.TencentCosProperties;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.CannedAccessControlList;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class CosService {
    private final TencentCosProperties tencentCosProperties;
    private final COSClient cosClient;

    public CosService(TencentCosProperties tencentCosProperties,
                      COSClient cosClient) {
        this.tencentCosProperties = tencentCosProperties;
        this.cosClient = cosClient;
    }

    /**
     * 上传文件到 COS
     * @param file MultipartFile
     * @param folder 文件夹名称，例如 videos / covers
     * @return 返回文件外链 URL
     */
    public String uploadFile(MultipartFile file, String folder) throws IOException {
        // 生成文件名，避免重复
        String originalFilename = file.getOriginalFilename();
        String suffix = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String fileName = UUID.randomUUID() + suffix;
        String key = folder + "/" + fileName;

        // MultipartFile 转 File（COS SDK 接受 File 对象）
        File localFile = convertMultipartFileToFile(file);

        try {
            // 上传文件到 COS
            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    tencentCosProperties.getBucketName(),
                    tencentCosProperties.getPrefix() + key,
                    localFile
            );
            putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead); // 文件可公开访问
            cosClient.putObject(putObjectRequest);
        } finally {
            // 上传完成后删除临时文件
            if (localFile.exists()) {
                localFile.delete();
            }
        }

        // 返回可访问 URL
        return tencentCosProperties.getUrlPrefix() + tencentCosProperties.getPrefix() + key;
    }

    // 工具方法：MultipartFile 转 File
    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convFile = File.createTempFile("upload", null);
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }
        return convFile;
    }

}
