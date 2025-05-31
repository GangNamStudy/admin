package com.parking.admin.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${api.base-url}")
    private String url;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(url)  // 필요한 경우 base URL 설정
                .build();
    }
}

