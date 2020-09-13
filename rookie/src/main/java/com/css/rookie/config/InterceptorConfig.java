package com.css.rookie.config;

import com.css.rookie.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <b>类名称：<b/>InterceptorConfig <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年8月20日 16:44 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 在此处，将拦截器注册为一个 Bean
     */
    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    /**
     * 拦截器配置
     * 在spring-mvc.xml配置文件内添加<mvc:interceptor>标签配置拦截器。
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 权限校验拦截器配置
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(                         //添加不拦截路径
                        "/**/*.html", "/**/*.js",
                        "/**/*.css", "/**/*.woff",
                        "/**/*.ttf", "/**/*.png",
                        "/**/*.jpg", "/**/*.jpeg",
                        "/**/*.gif", "/**/fonts/*",
                        "/**/*.svg", "/**/*.woff2"
                );
        // 父类的配置
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    /**
     * 配置静态资源访问路径，外部可以直接访问地址
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
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
