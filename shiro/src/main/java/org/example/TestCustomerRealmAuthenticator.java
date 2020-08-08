package org.example;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.example.realm.CustomerRealm;

import java.util.Arrays;

public class TestCustomerRealmAuthenticator {
    public static void main(String[] args) {

        //1.创建安全管理器
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        //2.设置自定义realm
        securityManager.setRealm(new CustomerRealm());

        //3.安全管理器设置到安全工具类中
        SecurityUtils.setSecurityManager(securityManager);

        //4.获取subject主题
        Subject subject = SecurityUtils.getSubject();

        //5.设置token令牌
        UsernamePasswordToken token = new UsernamePasswordToken("xpp", "123");

        try {
            System.out.println("认证状态:" + subject.isAuthenticated());
            subject.login(token);// 身份认证
            System.out.println("认证状态:" + subject.isAuthenticated());
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名密码错误！！");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("用户密码错误！！");
        }


        // 认证用户进行授权
        if (subject.isAuthenticated()) {

            //1.基于角色的权限控制
            System.out.println(subject.hasRole("admin"));

            //2.基于角色的权限控制
            System.out.println(subject.hasAllRoles(Arrays.asList("admin", "auditor")));

            //3.是否具有其中一个角色
            boolean[] booleans = subject.hasRoles(Arrays.asList("admin", "auditor", "user"));
            for (boolean aBoolean : booleans) {
                System.out.println(aBoolean);
            }

            System.out.println("=============");
            //基于权限字符串的访问控制 资源标识符:操作:资源类型
            System.out.println("权限：" + subject.isPermitted("user:*:*"));
        }

    }
}
