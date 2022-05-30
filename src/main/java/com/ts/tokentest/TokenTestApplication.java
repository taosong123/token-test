package com.ts.tokentest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ts.tokentest.mapper")
public class TokenTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TokenTestApplication.class, args);
    }

}
