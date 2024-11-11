package com.example.springmvcexamples.component;

import com.example.springmvcexamples.interception.AdminInterceptor;
import com.example.springmvcexamples.interception.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final AdminInterceptor adminInterceptor;
    private final LoginInterceptor loginInterceptor;
    /*
    * 1. addInterceptors方法用于注册拦截器
    * 2. addPathPatterns方法用于添加拦截路径
    * 3. excludePathPatterns方法用于排除拦截路径
    * 4. 通过registry.addInterceptor方法添加拦截器
    * 5. /**表示拦截所有路径，/*表示拦截一级路径
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login");
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/api/admin/**");

    }
}
