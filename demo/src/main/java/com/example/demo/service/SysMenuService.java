package com.example.demo.service;

import com.example.demo.entity.SysMenu;

import java.util.List;

public interface SysMenuService {

    /**
     * 查询用户拥有权限菜单
     * @param rid
     * @return List<SysMenu>
     */
    List<SysMenu> findSysMenuByRoleId(Integer rid);

}
