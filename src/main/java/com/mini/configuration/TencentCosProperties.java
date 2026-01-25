package com.mini.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "tencent.cos")
public class TencentCosProperties {
    private String secretId;
    private String secretKey;
    private String region;
    private String bucketName;
    private String prefix;
    private String urlPrefix;
}