package com.example.demo.controller;

import com.example.demo.annotation.MyRequiredPermission;
import com.example.demo.dao.SysUserDao;
import com.example.demo.entity.SysUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <b>类名称：<b/>Demo <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年7月20日 15:46 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
@RestController
public class Demo {

    @Resource
    private SysUserDao sysUserDao;

    @MyRequiredPermission("xx")
    @RequestMapping("user")
    public SysUser user(){
        return sysUserDao.selectByPrimaryKey(1);
    }
}
