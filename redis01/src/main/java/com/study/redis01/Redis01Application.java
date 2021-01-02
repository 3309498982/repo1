package com.study.redis01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.study.redis01.dao")
public class Redis01Application {

    public static void main(String[] args) {
        SpringApplication.run(Redis01Application.class, args);
    }

}
