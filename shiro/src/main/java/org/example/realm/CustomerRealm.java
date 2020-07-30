package org.example.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * <b>类名称：<b/>Customer <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年7月29日 9:19 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
public class CustomerRealm extends AuthorizingRealm {


    /**
     *  授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取principal
        String principal = (String) authenticationToken.getPrincipal();
        System.out.println(principal);
        //根据身份信息使用jdbc或mybatis查询相关数据库
        if ("xpp".equals(principal)) {
            //参数1：返回数据库中的正确的用户名
            //参数2：返回数据库中正确的密码
            //参数3：提供当前realm的名字 this.getName();
            return new SimpleAuthenticationInfo(principal,"123",this.getName());
        }
        return null;
    }
}
