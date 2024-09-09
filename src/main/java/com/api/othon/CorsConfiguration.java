package com.api.othon;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

  public void addCorsMappings(CorsRegistry registry) {
    //liberando app cliente 1
    registry.addMapping("/**")
         .allowedOrigins("*")
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");

    //liberando app cliente 2
    registry.addMapping("/java/**")
         .allowedOrigins("*")
        .allowedMethods("GET", "OPTIONS", "HEAD", "TRACE", "CONNECT");
}
}