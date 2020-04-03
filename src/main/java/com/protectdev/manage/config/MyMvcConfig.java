package com.protectdev.manage.config;

import com.protectdev.manage.interceptor.LoginHandleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //定义拦截器，拦截所有请求，排除一些请求
        registry.addInterceptor(new LoginHandleInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html",
                        "/page/register.html",
                        "/","/css/**","/JS/**",
                        "/login","/register","/phoneCode","/register/check","/login/check","/test");

    }
}
