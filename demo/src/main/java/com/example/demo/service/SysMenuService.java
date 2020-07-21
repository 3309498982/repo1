package com.example.demo.service;

import com.example.demo.entity.SysMenu;
import com.example.demo.entity.SysRole;

import java.util.List;

public interface SysMenuService {

    /**
     * 查询用户拥有权限菜单
     * @param roles
     * @return List<SysMenu>
     */
    List<SysMenu> findSysMenuByRole(List<SysRole> roles);

}
