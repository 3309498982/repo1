package com.css.rookie.service;

import com.css.rookie.entity.SysMenu;
import com.css.rookie.entity.SysUser;
import com.css.rookie.vo.MenuTree;

import java.util.List;

/**
 * <b>类名称：<b/>MenuManagerService <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年9月9日 10:49 <b/>
 * <b>修改备注：<b/><b/>
 * 
 * @version 1.0.0 <b/>
 */
public interface MenuManagerService {

    /**
     * 查询用户拥有菜单
     * @param userId
     * @return List<MenuTree>
     */
    List<MenuTree> queryMenuTreeByUserId(String userId);

    /**
     * 查询角色菜单
     * @param roleId
     * @return List<MenuTree>
     */
    List<MenuTree> queryMenuInfosByRoleId(String roleId);

    /**
     * 查询菜单列表
     * @return List<MenuTree>
     */
    List<MenuTree> queryMenuTree();

    /**
     * 新增或删除菜单
     * @param menuInfo
     * @param currentUser
     * @return Integer
     */
    Integer saveOrdDelMenuInfo(SysMenu menuInfo, SysUser currentUser);

    /**
     * 查询菜单详情
     * @param menuId
     * @return MenuInfo
     */
    SysMenu queryMenuInfoById(String menuId);

    /**
     * 修改菜单
     * @param menuInfo
     * @param currentUser
     * @return Integer
     */
    Integer updMenuInfo(SysMenu menuInfo, SysUser currentUser);
}
