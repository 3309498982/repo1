package com.css.rookie.service;

import com.css.rookie.common.CommonTabData;
import com.css.rookie.entity.SysRole;
import com.css.rookie.entity.SysUser;

import java.util.Map;

/**
 * <b>类名称：<b/>RoleManagerService <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年9月7日 19:45 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
public interface RoleManagerService {

    /**
     * 查询用户列表
     * @param condition
     * @return CommonTabData
     */
    CommonTabData queryPageDataByCondition(Map<String, Object> condition);

    /**
     * 查询角色信息
     * @param id
     * @return SysRole
     */
    SysRole queryRoleInfoById(String id);

    /**
     * 角色新增或修改
     * @param role
     * @param currentUser
     * @return Integer
     */
    Integer saveOrUpdRoleInfo(SysRole role, SysUser currentUser);

    /**
     * 角色删除
     * @param role
     * @param currentUser
     * @return Integer
     */
    Integer delRoleInfo(SysRole role, SysUser currentUser);

    /**
     * 分配菜单
     * @param condition
     * @param currentUser
     * @return Integer
     */
    Integer assigningMenusByCondition(Map<String, Object> condition, SysUser currentUser);

}
