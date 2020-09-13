package com.css.rookie.service.impl;

import com.css.rookie.common.CommonConstants;
import com.css.rookie.common.CommonTabData;
import com.css.rookie.common.log.CSSLogger;
import com.css.rookie.common.log.CSSLoggerFactory;
import com.css.rookie.dao.autogen.SysRoleMapper;
import com.css.rookie.dao.autogen.SysRoleMenuMapper;
import com.css.rookie.dao.manual.SysRoleDao;
import com.css.rookie.entity.SysRole;
import com.css.rookie.entity.SysRoleMenu;
import com.css.rookie.entity.SysUser;
import com.css.rookie.entity.SysUserRole;
import com.css.rookie.service.RoleManagerService;
import com.css.rookie.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RoleManagerServiceImpl implements RoleManagerService {

    private final CSSLogger log = CSSLoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public CommonTabData queryPageDataByCondition(Map<String, Object> condition) {
        List<SysRole> list = new ArrayList<>();
        CommonTabData data = null;
        int count = 0;
        try {
            Integer pageNo = (Integer) condition.get("pageNo");
            Integer pageSize = (Integer) condition.get("pageSize");
            if (pageNo == null || pageNo == 0) pageNo = 1;
            if (pageSize == null || pageSize == 0) pageSize = 10;
            int start = (pageNo - 1) * pageSize;
            condition.put("start", start);
            condition.put("pageSize", pageSize);
            count = sysRoleDao.queryRoleInfosCountByCondition(condition);
            if (count != 0) {
                list = sysRoleDao.queryRoleInfosByCondition(condition);
            }
            data = new CommonTabData(count, list, pageNo, pageSize);
        } catch (Exception e) {
            log.error("queryPageDataByCondition失败，原因{}", e);
        }
        return data;
    }

    @Override
    public SysRole queryRoleInfoById(String id) {
        SysRole roleInfo = null;
        try {
            roleInfo = sysRoleDao.queryRoleInfoById(id);
        } catch (Exception e) {
            log.error("queryRoleInfoById失败，原因{}", e);
        }
        return roleInfo;
    }

    @Override
    public Integer saveOrUpdRoleInfo(SysRole role, SysUser currentUser) {
        Integer count = null;
        Date date = new Date();
        try {
            // 补全数据
            role.setLastUpdateTime(date);
            role.setLastUpdateUserId(currentUser.getId());
            if (StringUtils.isEmpty(role.getId())) {
                if (sysRoleDao.queryRoleInfoByRoleName(role.getRoleName()) > 0) { return count; }
                role.setId(CommonUtil.getUUID());
                role.setCreateUserId(currentUser.getId());
                role.setCreateTime(date);
                role.setSfyx(CommonConstants.SFYX_TRUE);
                // 角色新增
                count = sysRoleMapper.insertSelective(role);
            } else {
                // 角色修改
                count = sysRoleMapper.updateByPrimaryKeySelective(role);
            }
        } catch (Exception e) {
            log.error("saveOrUpdRoleInfo失败，原因{}", e);
        }
        return count;
    }

    @Transactional
    @Override
    public Integer delRoleInfo(SysRole role, SysUser currentUser) {
        Integer count = null;
        try {
            // 角色删除
            role.setLastUpdateUserId(currentUser.getId());
            role.setLastUpdateTime(new Date());
            role.setSfyx(CommonConstants.SFYX_FALSE);
            count = sysRoleMapper.updateByPrimaryKeySelective(role);

            // 删除角色对用户应关系
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(role.getId());
            sysUserRole.setSfyx(CommonConstants.SFYX_FALSE);
            sysUserRole.setLastUpdateUserId(currentUser.getId());
            sysUserRole.setLastUpdateTime(new Date());
            count += sysRoleDao.delSysUserRoleByRoleId(sysUserRole);

            // 删除角色对应菜单关系
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(role.getId());
            sysRoleMenu.setSfyx(CommonConstants.SFYX_FALSE);
            sysRoleMenu.setLastUpdateUserId(currentUser.getId());
            sysRoleMenu.setLastUpdateTime(new Date());
            count += sysRoleDao.delSysRoleMenuByRoleId(sysRoleMenu);
        } catch (Exception e) {
            log.error("delRoleInfo失败，原因{}", e);
        }
        return count;
    }

    @Override
    public Integer assigningMenusByCondition(Map<String, Object> condition, SysUser currentUser) {
        Integer count = null;
        try {
            // 删除角色关联菜单
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(String.valueOf(condition.get("roleId")));
            sysRoleMenu.setSfyx(CommonConstants.SFYX_FALSE);
            sysRoleMenu.setLastUpdateUserId(currentUser.getId());
            sysRoleMenu.setLastUpdateTime(new Date());
            count = sysRoleDao.delSysRoleMenuByRoleId(sysRoleMenu);
            // 新增角色关联菜单
            List<String> menuIds = (List<String>) condition.get("menuIds");
            for (String menuId : menuIds) {
                sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setId(CommonUtil.getUUID());
                sysRoleMenu.setRoleId(String.valueOf(condition.get("roleId")));
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenu.setSfyx(CommonConstants.SFYX_TRUE);
                sysRoleMenu.setCreateUserId(currentUser.getId());
                sysRoleMenu.setCreateTime(new Date());
                sysRoleMenu.setLastUpdateUserId(currentUser.getId());
                sysRoleMenu.setLastUpdateTime(new Date());
                count += sysRoleMenuMapper.insert(sysRoleMenu);
            }
        } catch (Exception e) {
            log.error("assigningMenusByCondition失败，原因{}", e);
        }
        return count;
    }
}
