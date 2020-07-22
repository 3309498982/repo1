package com.example.demo.dao;

import com.example.demo.entity.SysUser;
import org.apache.ibatis.annotations.Param;

public interface SysUserDao {

    /**
     *  根据用户用户名和用户密码查询用户信息
     * @param username
     * @param password
     * @return SysUser
     */
    SysUser findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}