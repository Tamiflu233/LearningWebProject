package com.tamiflu.configuration;

import com.tamiflu.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer  {
    private TokenInterceptor tokenInterceptor;

    //构造方法
    public MyMvcConfig(TokenInterceptor tokenInterceptor){
        this.tokenInterceptor = tokenInterceptor;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*");
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer){
        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)));
        configurer.setDefaultTimeout(30000);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        List<String> excludePath = new ArrayList<>();
        //排除拦截，除了注册登录(此时还没token)，其他都拦截
        excludePath.add("/api/account/**");  //登录
        excludePath.add("/api/login/**");     //注册
        excludePath.add("/api/school/**");
        excludePath.add("/static/**");  //静态资源
        excludePath.add("/assets/**");  //静态资源
//        excludePath.add("/");
//        excludePath.add("/login");
//        excludePath.add("/register");
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/api/**")
                .addPathPatterns("/admin/**")
                .addPathPatterns("/student/**")
                .addPathPatterns("/teacher/**")
                .excludePathPatterns(excludePath);
        WebMvcConfigurer.super.addInterceptors(registry);
    }

}
