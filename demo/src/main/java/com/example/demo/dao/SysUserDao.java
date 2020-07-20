package com.example.demo.dao;

import com.example.demo.entity.SysUser;

public interface SysUserDao {

    SysUser selectByPrimaryKey(Integer id);

}