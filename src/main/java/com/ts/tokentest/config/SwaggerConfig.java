package com.ts.tokentest.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ts.tokentest.controller"))
                .paths(PathSelectors.any())
                .build()
                //要加上这两个全局配置
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());

    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("标题")
                .description("描述")
                .contact(new Contact("ts","123","123"))
                .version("2.2.0")
                .build();
    }

    private static List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList = new ArrayList();
        //name、keyname与下面的SecurityReference的reference参数必须相同
        //且必须与header中的token命名相同    header中  token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIU......
        apiKeyList.add(new ApiKey("token", "token", "header"));
        return apiKeyList;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts=new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build());
        return securityContexts;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences=new ArrayList<>();
        securityReferences.add(new SecurityReference("token", authorizationScopes));
        return securityReferences;
       // return Arrays.asList(new SecurityReference("token", authorizationScopes));
    }
}
