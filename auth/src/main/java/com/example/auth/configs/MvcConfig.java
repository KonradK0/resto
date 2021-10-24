package com.example.auth.configs;

import com.example.auth.interceptor.RestInterceptorAll;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    private final RestInterceptorAll restInterceptorAll;

    public MvcConfig(RestInterceptorAll restInterceptorAll) {
        this.restInterceptorAll = restInterceptorAll;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.restInterceptorAll);
    }
}
