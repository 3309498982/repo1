package com.example.springboot_shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <b>类名称：<b/>TestController <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年7月30日 10:56 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
@Controller
public class TestController {

    @RequestMapping("loginPage")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("login")
    public String login(String username, String password) {
        //获取主题对象subject
        Subject subject = SecurityUtils.getSubject();
        //获取token令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);//认证
            return "index";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误");
        }
        return "login";
    }

}
