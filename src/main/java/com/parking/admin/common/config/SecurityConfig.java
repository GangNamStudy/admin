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

        http
                .csrf(csrf -> csrf.disable())  // CSRF 보호 비활성화
                .cors(cors -> cors.disable())  // CORS 비활성화 (필요시)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(whitelist).permitAll()
                        .anyRequest().authenticated()
                );

         return http.build();
    }
}
