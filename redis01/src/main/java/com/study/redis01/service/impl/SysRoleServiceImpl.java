package com.study.redis01.service.impl;

import com.study.redis01.dao.SysRoleDao;
import com.study.redis01.entity.SysRole;
import com.study.redis01.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public void updateById(SysRole role) {
        sysRoleDao.updateById(role);
    }
}
