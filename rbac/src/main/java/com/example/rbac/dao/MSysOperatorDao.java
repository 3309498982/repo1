package com.example.rbac.dao;

import com.example.rbac.entity.MSysOperator;

public interface MSysOperatorDao {

    /**
     *  根据用户名查询用户信息
     * @param username
     * @return
     */
    MSysOperator findByOperator(String username);

}
