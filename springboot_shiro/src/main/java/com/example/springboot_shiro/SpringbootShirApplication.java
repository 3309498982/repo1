package com.example.springboot_shiro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringbootShirApplication extends SpringBootServletInitializer {

    private final static Logger log = LoggerFactory.getLogger(SpringbootShirApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShirApplication.class, args);
        log.info("项目启动！！");
        log.info("http://localhost:8080/index");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringbootShirApplication.class);
    }

}
