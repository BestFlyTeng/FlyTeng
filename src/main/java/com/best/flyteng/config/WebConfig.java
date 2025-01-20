package com.best.flyteng.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
            .addMapping("/**")
            .allowedOrigins("*")  // 允许特定的源
            .allowedMethods("GET", "POST","PUT","PATCH","DELETE","OPTION")  // 允许的请求方法
            .allowedHeaders("Content-Type", "Authorization");  // 允许的请求头
  }
}