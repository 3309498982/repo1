package com.example.demo.dao;

import com.example.demo.entity.SysRole;

import java.util.List;

/**
 * <b>类名称：<b/>SysRoleDao <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年7月21日 9:48 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
public interface SysRoleDao {

    /**
     * 查询用户角色信息
     * @param uid
     * @return List<SysRole>
     */
    List<SysRole> findBySysUserId(Integer uid);
}
