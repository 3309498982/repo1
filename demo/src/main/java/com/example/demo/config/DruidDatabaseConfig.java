package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * <b>类名称：<b/>DruidDatabaseConfig <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年7月20日 15:02 <b/>
 * <b>修改备注：<b/><b/>
 * 
 * @version 1.0.0 <b/>
 */
@Configuration
public class DruidDatabaseConfig {

    private final static Logger logger = LoggerFactory.getLogger(DruidDatabaseConfig.class);

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        logger.info("druidDataSource实例化");
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

}
