package com.strangehoon.courseregistration.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //로그인한 학생들만 접근 가능
        registry.addInterceptor(new LoginCheckInterceptor())
                .addPathPatterns("/home", "/partClasses/**","/pocketList", "/board/list", "/register/**")
                .excludePathPatterns(
                        "/", "/css/**", "/*.ico", "/error"
                );

        //로그인한 관리자만 접근 가능
        registry.addInterceptor(new LoginManagerCheckInterceptor())
                .addPathPatterns("managerBoard/**", "/managerPartClasses/**")
                .excludePathPatterns(
                        "/", "/css/**", "/*.ico", "/error"
                );
    }

}