package com.example.stablefordscoringservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins("https://master.dg1gxs71ljkjp.amplifyapp.com")
                .allowedOrigins("http://172.23.192.1:3000")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("PUT", "POST", "GET")
                .allowedHeaders("Origin", "Content-Type", "X-Frame-Options", "Accept")
                .allowCredentials(false).maxAge(3600);
        // Add more mappings...
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().disable();
        http.csrf().disable();
        http
                // ...
                .requiresChannel(channel -> channel
                        .anyRequest().requiresSecure()
                );
        return http.build();
    }
}
