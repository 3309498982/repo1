package com.example.rbac.dao;

import com.example.rbac.entity.MSysRole;

import java.util.List;

public interface MSysRoleDao {

    List<MSysRole> findByOperatorId(Integer rid);
}
