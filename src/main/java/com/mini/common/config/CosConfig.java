package com.mini.common.config;

import com.mini.common.domain.TencentCosProperties;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CosConfig {

    private final TencentCosProperties tencentCosProperties;

    public CosConfig(TencentCosProperties tencentCosProperties) {
        this.tencentCosProperties = tencentCosProperties;
    }

    @Bean
    public COSClient cosClient() {
        COSCredentials credentials = new BasicCOSCredentials(
                tencentCosProperties.getSecretId(),
                tencentCosProperties.getSecretKey()
        );
        ClientConfig clientConfig = new ClientConfig(new Region(tencentCosProperties.getRegion()));
        return new COSClient(credentials, clientConfig);
    }
}

