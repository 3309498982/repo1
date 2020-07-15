package com.example.rbac.service.impl;

import com.example.rbac.dao.MSysPermissionDao;
import com.example.rbac.entity.MSysPermission;
import com.example.rbac.entity.MSysRole;
import com.example.rbac.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <b>类名称：</b>PermissionServiceIml<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>刘亚州<br/>
 * <b>修改人：</b>刘亚州<br/>
 * <b>修改时间：</b>2020/7/14 10:41<br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0<br />
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private MSysPermissionDao mSysPermissionDao;

    @Override
    public List<MSysPermission> findByRolePermission(MSysRole role) {
        return mSysPermissionDao.findByRoleId(role.getId());
    }
}
