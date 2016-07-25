package com.pgs.intern.configurations;

import com.pgs.intern.interceptors.AuthenticationBasedInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by lschiffer on 7/25/2016.
 */
@Configuration
public class HandlerInterceptorConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    AuthenticationBasedInterceptor authenticationBasedInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationBasedInterceptor).excludePathPatterns("/", "/login", "/register");
    }
}
