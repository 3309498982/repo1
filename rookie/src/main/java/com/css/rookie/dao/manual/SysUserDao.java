package com.css.rookie.dao.manual;

import com.css.rookie.entity.SysUser;
import com.css.rookie.entity.SysUserRole;
import com.css.rookie.vo.SysRoleVo;

import java.util.HashMap;
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
public interface SysUserDao {

    /**
     * 查询用户信息
     * @param condition
     * @return SysUser
     */
    SysUser queryUserInfoByCondition(HashMap<String, String> condition);

    /**
     * 根据条件用户量
     * @param condition
     * @return Integer
     */
    Integer queryUserInfosCountByCondition(Map<String, Object> condition);

    /**
     * 分页查询用户列表
     * @param condition
     * @return List<SysUser>
     */
    List<SysUser> queryUserInfosByCondition(Map<String, Object> condition);

    /**
     * 查询用户名相同用户信息数量
     * @param username
     * @return Integer
     */
    Integer queryUserInfoCount(String username);

    /**
     * 查询角色信息
     * @return List<SysRoleVo>
     */
    List<SysRoleVo> queryRoleInfos();

    /**
     * 查询用户角色信息
     * @param userId
     * @return List<SysRoleVo>
     */
    List<SysRoleVo> queryRoleInfosByUserId(String userId);

    /**
     * 删除用户对应的角色关系
     * @param sysUserRole
     * @return Integer
     */
    Integer delSysUserRoleByUserId(SysUserRole sysUserRole);

    /**
     * 查询用户权限
     * @param userId
     * @return List<String>
     */
    List<String> queryPermissionByUserId(String userId);
}
