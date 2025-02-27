package com.parking.admin.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Value("${security.whitelist}") // yml에서 배열 값 읽기
    private String[] whitelist;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

         http.authorizeHttpRequests(auth -> auth
                        .requestMatchers(whitelist).permitAll() // yml에서 가져온 경로들을 인증 없이 허용
                        .anyRequest().authenticated() // 나머지는 인증 필요
                );

         return http.build();
    }
}
