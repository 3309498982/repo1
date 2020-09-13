package com.css.rookie.service;

import com.css.rookie.common.CommonTabData;
import com.css.rookie.entity.SysUser;
import com.css.rookie.vo.SysRoleVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <b>类名称：<b/>UserManagerService <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年9月7日 19:45 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
public interface UserManagerService {

    /**
     * 查询用户信息
     * @param username
     * @return SysUser
     */
    SysUser queryUserInfoByUsername(String username);

    /**
     * 查询用户列表
     * @param condition
     * @return CommonTabData
     */
    CommonTabData queryPageDataByCondition(Map<String, Object> condition);

    /**
     * 用户新增或修改
     * @param user
     * @param currentUser
     * @return Integer
     */
    Integer saveOrUpdUserInfo(SysUser user, SysUser currentUser);

    /**
     * 查询用户信息
     * @param id
     * @return UserInfo
     */
    SysUser queryUserInfoById(String id);

    /**
     * 用户删除
     * @param user
     * @param currentUser
     * @return Integer
     */
    Integer delUserInfo(SysUser user, SysUser currentUser);

    /**
     * 用户角色信息
     * @param userId
     * @return List<SysRoleVo>
     */
    List<SysRoleVo> queryRoleInfosByUserId(String userId);

    /**
     * 用户分配角色
     * @param condition
     * @param currentUser
     * @return Integer
     */
    Integer assigningRoles(HashMap<String, Object> condition, SysUser currentUser);

    /**
     * 获取用户权限信息
     * @param userId
     * @return Set<String>
     */
    Set<String> getPermissionSet(String userId);

}
