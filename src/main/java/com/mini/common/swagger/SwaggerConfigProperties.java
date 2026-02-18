package com.mini.common.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serial;
import java.io.Serializable;

@Data
@ConfigurationProperties(prefix = "nz.swagger")
public class SwaggerConfigProperties implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Boolean enable = false;
    private Boolean enableResponseWrap = false;

    public String packagePath;
    public String title;
    public String description;
    public String contactName;
    public String contactUrl;
    public String contactEmail;
    public String version;

}
