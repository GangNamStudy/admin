package com.parking.admin.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${api.payment.base-url}")
    private String paymentUrl;

    @Value("${api.item-management.base-url}")
    private String itemManagementUrl;

    @Value("${api.parking-management.base-url}")
    private String parkingManagementUrl;

    @Bean
    public WebClient paymentWebClient() {
        return WebClient.builder()
                .baseUrl(paymentUrl)
                .build();
    }

    @Bean
    public WebClient itemManagementWebClient() {
        return WebClient.builder()
                .baseUrl(itemManagementUrl)
                .build();
    }

    @Bean
    public WebClient parkingManagementWebClient() {
        return WebClient.builder()
                .baseUrl(parkingManagementUrl)
                .build();
    }
}

