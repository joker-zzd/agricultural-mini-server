package com.mini.common.service;

import com.mini.common.domain.TencentCosProperties;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class CosService {
    private final TencentCosProperties tencentCosProperties;

    public CosService(TencentCosProperties tencentCosProperties) {
        this.tencentCosProperties = tencentCosProperties;
    }

    @Bean
    public COSClient cosClient() {
        COSCredentials credentials = new BasicCOSCredentials(tencentCosProperties.getSecretId(),
                tencentCosProperties.getSecretKey());
        ClientConfig clientConfig = new ClientConfig(new Region(tencentCosProperties.getRegion()));
        return new COSClient(credentials, clientConfig);
    }

    /**
     * 上传文件到 COS
     * @param file MultipartFile
     * @param folder 文件夹名称，例如 videos / covers
     * @return 返回文件外链 URL
     */

}
