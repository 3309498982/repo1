package com.css.rookie.service.impl;

import com.css.rookie.common.BusinessConstants;
import com.css.rookie.common.CommonConstants;
import com.css.rookie.common.CommonTabData;
import com.css.rookie.common.log.CSSLogger;
import com.css.rookie.common.log.CSSLoggerFactory;
import com.css.rookie.dao.autogen.SysUserMapper;
import com.css.rookie.dao.autogen.SysUserRoleMapper;
import com.css.rookie.dao.manual.SysUserDao;
import com.css.rookie.entity.SysUser;
import com.css.rookie.entity.SysUserRole;
import com.css.rookie.service.UserManagerService;
import com.css.rookie.util.CommonUtil;
import com.css.rookie.vo.SysRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * <b>类名称：<b/>UserManagerServiceImpl <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年8月13日 14:30 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
@Service
public class UserManagerServiceImpl implements UserManagerService {


    private final CSSLogger log = CSSLoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public SysUser queryUserInfoByUsername(String username) {
        SysUser userInfo = null;
        try {
            HashMap<String, String> condition = new HashMap<>();
            condition.put("username", username);
            userInfo = sysUserDao.queryUserInfoByCondition(condition);
        } catch (Exception e) {
            log.error("queryUserInfoByUsername失败，原因{}", e);
        }
        return userInfo;
    }

    @Override
    public CommonTabData queryPageDataByCondition(Map<String, Object> condition) {
        List<SysUser> list = new ArrayList<>();
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
            count = sysUserDao.queryUserInfosCountByCondition(condition);
            if (count != 0) {
                list = sysUserDao.queryUserInfosByCondition(condition);
            }
            data = new CommonTabData(count, list, pageNo, pageSize);
        } catch (Exception e) {
            log.error("queryPageDataByCondition失败，原因{}", e);
        }
        return data;
    }

    @Override
    public Integer saveOrUpdUserInfo(SysUser user, SysUser currentUser) {
        Integer count = null;
        Date date = new Date();
        try {
            // 补全数据
            user.setLastUpdateTime(date);
            user.setLastUpdateUserId(currentUser.getId());
            if (user.getId() == null) {
                if (sysUserDao.queryUserInfoCount(user.getUsername()) > 0) { return count; }
                user.setId(CommonUtil.getUUID());
                user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
                user.setIsAdmin(BusinessConstants.IS_ADMIN_FALSE);
                user.setIsFreeze(BusinessConstants.STATUS_FREEZE_FALSE);
                user.setSfyx(CommonConstants.SFYX_TRUE);
                user.setCreateUserId(currentUser.getId());
                user.setCreateTime(date);
                // 用户新增
                count = sysUserMapper.insertSelective(user);
            } else {
                // 用户修改
                count = sysUserMapper.updateByPrimaryKeySelective(user);
            }
        } catch (Exception e) {
            log.error("saveOrUpdUserInfo失败，原因{}", e);
        }
        return count;
    }

    @Override
    public SysUser queryUserInfoById(String id) {
        SysUser userInfo = null;
        try {
            HashMap<String, String> condition = new HashMap<>();
            condition.put("id", id);
            userInfo = sysUserDao.queryUserInfoByCondition(condition);
        } catch (Exception e) {
            log.error("queryUserInfoById失败，原因{}", e);
        }
        return userInfo;
    }

    @Transactional
    @Override
    public Integer delUserInfo(SysUser user, SysUser currentUser) {
        Integer count = null;
        try {
            // 用户修改
            user.setLastUpdateTime(new Date());
            user.setLastUpdateUserId(currentUser.getId());
            user.setSfyx(CommonConstants.SFYX_FALSE);
            count = sysUserMapper.updateByPrimaryKeySelective(user);

            // 用户对应角色关系修改
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(user.getId());
            sysUserRole.setSfyx(CommonConstants.SFYX_FALSE);
            sysUserRole.setLastUpdateUserId(currentUser.getId());
            sysUserRole.setLastUpdateTime(new Date());
            count += sysUserDao.delSysUserRoleByUserId(sysUserRole);
        } catch (Exception e) {
            log.error("delUserInfo失败，原因{}", e);
        }
        return count;
    }

    @Override
    public List<SysRoleVo> queryRoleInfosByUserId(String userId) {
        List<SysRoleVo> allSysRoleVos = null;
        try {
            //查询全部角色信息
            allSysRoleVos = sysUserDao.queryRoleInfos();
            //查询角色拥有的角色信息
            List<SysRoleVo> sysRoleVos = sysUserDao.queryRoleInfosByUserId(userId);

            if (!CollectionUtils.isEmpty(allSysRoleVos) && !CollectionUtils.isEmpty(sysRoleVos)) {
                for (SysRoleVo allSysRoleVo : allSysRoleVos) {
                    for (SysRoleVo sysRoleVo : sysRoleVos) {
                        if (sysRoleVo.getId().equals(allSysRoleVo.getId())) { //用户拥有的角色
                            allSysRoleVo.setChecked("checked");
                        }
                    }
                }
            } else if(CollectionUtils.isEmpty(sysRoleVos)) {
                return allSysRoleVos;
            }
        } catch (Exception e) {
            log.error("queryRoleInfosByUserId失败，原因{}", e);
        }
        return allSysRoleVos;
    }

    @Transactional
    @Override
    public Integer assigningRoles(HashMap<String, Object> condition, SysUser currentUser) {
        Integer count = null;
        try {
            // 删除用户关联角色
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(String.valueOf(condition.get("id")));
            sysUserRole.setSfyx(CommonConstants.SFYX_FALSE);
            sysUserRole.setLastUpdateUserId(currentUser.getId());
            sysUserRole.setLastUpdateTime(new Date());
            count = sysUserDao.delSysUserRoleByUserId(sysUserRole);

            // 新增用户关联角色
            List<String> roleIds = (List<String>) condition.get("roleIds");
            for (String roleId : roleIds) {
                sysUserRole = new SysUserRole();
                sysUserRole.setId(CommonUtil.getUUID());
                sysUserRole.setUserId(String.valueOf(condition.get("id")));
                sysUserRole.setRoleId(roleId);
                sysUserRole.setSfyx(CommonConstants.SFYX_TRUE);
                sysUserRole.setCreateUserId(currentUser.getId());
                sysUserRole.setCreateTime(new Date());
                sysUserRole.setLastUpdateUserId(currentUser.getId());
                sysUserRole.setLastUpdateTime(new Date());
                count += sysUserRoleMapper.insert(sysUserRole);
            }
        } catch (Exception e) {
            log.error("assigningRoles失败，原因{}", e);
        }
        return count;
    }

    @Override
    public Set<String> getPermissionSet(String userId) {
        // 根据用户名查询当前用户权限
        Set<String> set = null;
        try {
            set = new HashSet<>();
            List<String> permissions = sysUserDao.queryPermissionByUserId(userId);
            for (String permission : permissions) {
                if (!StringUtils.isEmpty(permission)) {
                    set.add(permission);
                }
            }
        } catch (Exception e) {
            log.error("getPermissionSet失败，原因{}", e);
        }
        return set;
    }
}
