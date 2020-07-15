package com.example.rbac.service.impl;

import com.example.rbac.dao.MSysRoleDao;
import com.example.rbac.entity.MSysOperator;
import com.example.rbac.entity.MSysRole;
import com.example.rbac.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <b>类名称：</b>MSysRoleServiceImpl<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>刘亚州<br/>
 * <b>修改人：</b>刘亚州<br/>
 * <b>修改时间：</b>2020/7/14 10:13<br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0<br />
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private MSysRoleDao mSysRoleDao;

    @Override
    public List<MSysRole> findByOperatorRole(MSysOperator operator) {
        return mSysRoleDao.findByOperatorId(operator.getId());
    }
}
