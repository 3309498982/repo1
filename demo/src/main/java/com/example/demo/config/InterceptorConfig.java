package com.example.demo.config;

import com.example.demo.Interceptor.MyPermissionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <b>类名称：<b/>InterceptorConfig <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年7月20日 16:44 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 拦截器配置
     * 在spring-mvc.xml配置文件内添加<mvc:interceptor>标签配置拦截器。
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 权限校验拦截器配置
        registry.addInterceptor(new MyPermissionInterceptor()).addPathPatterns("/**");
        // 父类的配置
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    /**
     * 跨域支持
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")  //浏览器允许所有的域访问 / 注意 * 不能满足带有cookie的访问,Origin 必须是全匹配
                .allowCredentials(true)   // 允许带cookie访问
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("token")
                .maxAge(3600);
    }
}
