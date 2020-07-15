package com.example.rbac.dao;

import com.example.rbac.entity.MSysPermission;

import java.util.List;

public interface MSysPermissionDao {

    /**
     * 根据角色id查询权限信息
     * @param roleId
     * @return
     */
    List<MSysPermission> findByRoleId(Integer roleId);
}
