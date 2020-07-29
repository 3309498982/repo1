package org.example.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * <b>类名称：<b/>TestCustomerRealmAuthenticator <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年7月29日 9:47 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
public class TestCustomerRealmAuthenticator {
    public static void main(String[] args) {

        //1.创建securityManager
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        //2.设置自定义realm
        securityManager.setRealm(new CustomerRealm());

        //将自定义realm设置到SecurityUtils中
        SecurityUtils.setSecurityManager(securityManager);

        //通过安全工具类获取subject
        Subject subject = SecurityUtils.getSubject();

        //创建token令牌
        UsernamePasswordToken token = new UsernamePasswordToken("xpp", "123");

        try {
            System.out.println("认证状态：" + subject.isAuthenticated());
            subject.login(token);//认证
            System.out.println("认证状态：" + subject.isAuthenticated());
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("认证失败：用户名不存在");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("认证失败：密码错误");
        }
    }
}
