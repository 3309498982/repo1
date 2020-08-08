package org.example.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Arrays;

public class CustomerRealm extends AuthorizingRealm {

    // 授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String principal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("身份信息：" + principal);

        //根据身份信息 获取当前身份角色信息，以及权限信息 admin
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //将数据库中查询到的角色信息赋值给权限对象
        simpleAuthorizationInfo.addRoles(Arrays.asList("admin", "auditor"));

        //将数据库中查询到的角色信息赋值给权限对象
        simpleAuthorizationInfo.addStringPermission("user:*:*");
        return simpleAuthorizationInfo;
    }

    // 认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取身份信息
        String principal = (String) authenticationToken.getPrincipal();
        System.out.println(principal);

        if (principal.equals("xpp")) {
            return new SimpleAuthenticationInfo("xpp", "123", this.getName());
        }
        return null;
    }
}
