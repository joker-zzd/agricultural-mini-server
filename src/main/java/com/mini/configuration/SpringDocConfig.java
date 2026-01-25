package com.mini.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI createAPI() {
        return new OpenAPI().
                info(new Info().title("农资商城接口文档")
                        .description("基于SpringBoot项目构建")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0"))
                        .contact(new Contact().name("黄显军").email("1925638055@qq.com")));
    }
}
