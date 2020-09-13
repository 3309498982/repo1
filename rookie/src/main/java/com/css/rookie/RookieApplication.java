package com.css.rookie;

import com.css.rookie.common.log.CSSLogger;
import com.css.rookie.common.log.CSSLoggerFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan(basePackages = {"com.css.rookie.dao"})
public class RookieApplication extends SpringBootServletInitializer {

    private static CSSLogger log = CSSLoggerFactory.getLogger(RookieApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RookieApplication.class, args);
        log.info("项目启动：http://localhost:8080/rookie/loginPage");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RookieApplication.class);
    }

}
