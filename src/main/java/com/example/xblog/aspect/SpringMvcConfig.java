package com.example.xblog.aspect;

import com.example.xblog.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    LoginInterceptor loginInterceptor;

    @Value("${file.save.location}")
    private String fileSaveLocation;  // 资源在本地磁盘的路径

    @Value("${file.request.path}")
    private String fileRequestPath;  // 资源访问路径

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/user/login",
                        "/user/register",
                        "/user/{username}",
                        "/user/save",
                        "/article/list",
                        "/article/detail",
                        "/file/**",

                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/images/**",
                        "/webjars/**",
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/configuration/security"
                        );

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //注册配置类，使用addResourceHandlers方法，将本地路径 "file:" + fileSaveLocation映射到fileRequestPath路由上。
        registry.addResourceHandler(fileRequestPath).addResourceLocations("file:" + fileSaveLocation);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
