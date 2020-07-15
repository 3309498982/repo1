package com.example.rbac.service;

import com.example.rbac.entity.MSysPermission;
import com.example.rbac.entity.MSysRole;

import java.util.List;

public interface PermissionService {

    List<MSysPermission> findByRolePermission(MSysRole role);
}
