package com.example.templates;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TemplatesApplication extends SpringBootServletInitializer {

    private final static Logger log = LoggerFactory.getLogger(TemplatesApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TemplatesApplication.class, args);
        log.info("项目启动成功！！！");
        log.info("http://localhost:8080/index");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TemplatesApplication.class);
    }
}
