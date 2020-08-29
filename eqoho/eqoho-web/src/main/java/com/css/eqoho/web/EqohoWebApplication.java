package com.css.eqoho.web;

import com.css.common.log.CSSLogger;
import com.css.common.log.CSSLoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class EqohoWebApplication extends SpringBootServletInitializer {

    private static CSSLogger logger = CSSLoggerFactory.getLogger(EqohoWebApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EqohoWebApplication.class, args);
        logger.info("项目启动成功");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EqohoWebApplication.class);
    }

}
