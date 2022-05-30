package com.ts.tokentest.config;


import com.ts.tokentest.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                //拦截此请求，交给JWTInterceptor拦截器，返回为true后才进入controller具体方法中；返回为false则抛出异常
                .addPathPatterns("/verify")
                //此请求放行，不拦截
                .excludePathPatterns("/login");
    }
}
