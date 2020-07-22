package com.example.demo.dao;

import com.example.demo.entity.SysMenu;
import com.example.demo.entity.SysRole;

import java.util.List;

/**
 * <b>类名称：<b/>SysMenuDao <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年7月21日 10:28 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
public interface SysMenuDao {

    /**
     * 查询用户拥有权限菜单
     * @param roles
     * @return List<SysMenu>
     */
    List<SysMenu> findSysMenuByRole(List<SysRole> roles);
}
