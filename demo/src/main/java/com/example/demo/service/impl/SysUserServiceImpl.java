package com.example.demo.service.impl;

import com.example.demo.dao.SysUserDao;
import com.example.demo.entity.SysUser;
import com.example.demo.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <b>类名称：<b/>SysUserServiceImpl <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年7月21日 9:03 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserDao userDao;

    @Override
    public SysUser login(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }
}
