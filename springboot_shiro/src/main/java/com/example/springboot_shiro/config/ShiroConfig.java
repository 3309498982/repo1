package com.example.springboot_shiro.config;

import com.example.springboot_shiro.shiro.shiro.CustomerRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * <b>类名称：<b/>ShiroConfig <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年7月30日 10:33 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
@Configuration
public class ShiroConfig {

    //1.创建shiroFilter
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //配置系统受限制资源
        //配置系统公共资源
        HashMap<String, String> map = new HashMap<>();
        map.put("/index", "authc");  //请求这个资源需要认证和授权
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        //默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("login");
        return shiroFilterFactoryBean;
    }

    //2.创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        //给安全管理器设置自定义realm
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    //3.创建自定义realm
    @Bean
    public Realm getRealm() {
        return new CustomerRealm();
    }
}
