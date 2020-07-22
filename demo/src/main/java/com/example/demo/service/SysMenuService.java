package com.example.demo.service;

import com.example.demo.entity.SysMenu;
import com.example.demo.entity.SysRole;

import java.util.List;
import java.util.Set;

public interface SysMenuService {

    /**
     * 查询用户拥有权限菜单
     * @param roles
     * @return List<SysMenu>
     */
    List<SysMenu> findSysMenuByRole(List<SysRole> roles);


    /**
     * 查询用户拥有权限菜单
     * @param roles
     * @return Set<String>
     */
    Set<String> findUserPermission(List<SysRole> roles);

}
