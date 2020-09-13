package com.css.rookie.dao.manual;

import com.css.rookie.entity.SysRole;
import com.css.rookie.entity.SysRoleMenu;
import com.css.rookie.entity.SysUserRole;

import java.util.List;
import java.util.Map;

/**
 * <b>类名称：<b/>UserInfoDao <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年9月8日 8:28 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
public interface SysRoleDao {

    /**
     * 根据条件角色量
     * @param condition
     * @return Integer
     */
    Integer queryRoleInfosCountByCondition(Map<String, Object> condition);

    /**
     * 查询角色信息
     * @param condition
     * @return List<RoleInfo>
     */
    List<SysRole> queryRoleInfosByCondition(Map<String, Object> condition);

    /**
     * 查询角色信息
     * @param id
     * @return RoleInfo
     */
    SysRole queryRoleInfoById(String id);

    /**
     * 查询相同角色
     * @param roleName
     * @return Integer
     */
    Integer queryRoleInfoByRoleName(String roleName);

    /**
     * 删除用户角色表信息
     * @param userToRole
     * @return Integer
     */
    Integer delSysUserRoleByRoleId(SysUserRole userToRole);

    /**
     * 删除角色菜单表信息
     * @param RoleToMenu
     * @return Integer
     */
    Integer delSysRoleMenuByRoleId(SysRoleMenu RoleToMenu);
}
