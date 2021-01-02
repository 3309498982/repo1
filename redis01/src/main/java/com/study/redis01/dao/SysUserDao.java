package com.study.redis01.dao;

import com.study.redis01.entity.SysUser;

import java.util.List;

public interface SysUserDao {

    List<SysUser> findAll();

    void save(SysUser user);

    void deleteById(String id);

    List<SysUser> findUserRole();
}
