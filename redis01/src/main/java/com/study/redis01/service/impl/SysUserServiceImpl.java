package com.study.redis01.service.impl;

import com.study.redis01.dao.SysUserDao;
import com.study.redis01.entity.SysUser;
import com.study.redis01.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public List<SysUser> findAll() {
        return sysUserDao.findAll();
    }

    @Override
    public void save(SysUser user) {
        sysUserDao.save(user);
    }

    @Override
    public void deleteById(String id) {
        sysUserDao.deleteById(id);
    }

    @Override
    public List<SysUser> findUserRole() {
        return sysUserDao.findUserRole();
    }
}
