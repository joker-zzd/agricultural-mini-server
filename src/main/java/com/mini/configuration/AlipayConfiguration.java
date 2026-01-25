package com.mini.configuration;

import com.alipay.api.AlipayClient;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@EqualsAndHashCode(callSuper = true)
@Component
@ConfigurationProperties(prefix = "alipay")
@Data
public class AlipayConfiguration extends Throwable {

    private String appId;

    private String privateKey;

    private String publicKey;

    private String gatewayUrl;

    private String notifyUrl;

    private String returnUrl;

    private String signType;

    private String charset;

    private String format;

    private AlipayClient alipayClient = null;

}
