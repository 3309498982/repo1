package com.example.demo.service.impl;

import com.example.demo.dao.SysRoleDao;
import com.example.demo.entity.SysRole;
import com.example.demo.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <b>类名称：<b/>SysRoleServiceImpl <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年7月21日 9:46 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleDao roleDao;

    @Override
    public List<SysRole> findSysRoleBySysUserId(Integer uid) {
        return roleDao.findSysRoleBySysUserId(uid);
    }
}
