package com.example.demo.service;

import com.example.demo.entity.SysUser;

/**
 * <b>类名称：<b/>SysUserService <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年7月21日 9:02 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
public interface SysUserService {

    /**
     *  用户登录
     * @param username
     * @param password
     * @return SysUser
     */
    SysUser login(String username, String password);
}
