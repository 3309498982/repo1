package com.example.demo.controller;

import com.example.demo.entity.SysMenu;
import com.example.demo.entity.SysRole;
import com.example.demo.entity.SysUser;
import com.example.demo.service.SysMenuService;
import com.example.demo.service.SysRoleService;
import com.example.demo.service.SysUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <b>类名称：<b/>LoginController <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年7月21日 8:57 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
@RestController
public class LoginController {

    @Resource
    private SysUserService userService;

    @Resource
    private SysRoleService roleService;

    @Resource
    private SysMenuService menuService;

    @RequestMapping("login")
    public List<SysMenu> login(String username, String password){
        SysUser u = userService.login(username, password);
        // 查询用户属于那种角色
        List<SysRole> roles = roleService.findSysRoleBySysUserId(u.getId());
        // 查询用户拥有权限菜单
        List<SysMenu> sysMenus = menuService.findSysMenuByRole(roles);
        return sysMenus;
    }
}
