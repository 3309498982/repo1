package com.example.rbac.service;

import com.example.rbac.entity.MSysOperator;
import com.example.rbac.entity.MSysRole;

import java.util.List;

public interface RoleService {

    //根据用户获取角色信息
    List<MSysRole> findByOperatorRole(MSysOperator operator);
}
