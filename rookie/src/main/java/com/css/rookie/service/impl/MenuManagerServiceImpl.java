package com.css.rookie.service.impl;

import com.css.rookie.common.CommonConstants;
import com.css.rookie.common.log.CSSLogger;
import com.css.rookie.common.log.CSSLoggerFactory;
import com.css.rookie.dao.autogen.SysMenuMapper;
import com.css.rookie.dao.manual.SysMenuDao;
import com.css.rookie.dao.manual.SysUserDao;
import com.css.rookie.entity.SysMenu;
import com.css.rookie.entity.SysUser;
import com.css.rookie.service.MenuManagerService;
import com.css.rookie.util.CommonUtil;
import com.css.rookie.vo.MenuTree;
import com.css.rookie.vo.State;
import com.css.rookie.vo.SysRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * <b>类名称：<b/>MenuManagerServiceImpl <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年8月19日 8:52 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
@Service
public class MenuManagerServiceImpl implements MenuManagerService {

    private final CSSLogger log = CSSLoggerFactory.getLogger(this.getClass());

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysMenuDao sysMenuDao;

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public List<MenuTree> queryMenuTreeByUserId(String userId) {
        List<MenuTree> rootMenu = null;
        try {
            //查询角色拥有的角色信息
            List<SysRoleVo> roleInfoVos = sysUserDao.queryRoleInfosByUserId(userId);
            if (CollectionUtils.isEmpty(roleInfoVos)) {
                return null;
            }

            List<String> roleIds = new ArrayList<>();
            for (SysRoleVo roleInfoVo : roleInfoVos) {
                roleIds.add(roleInfoVo.getId());
            }

            //根节点
            rootMenu = new ArrayList<>();
            //查询角色下的菜单
            List<MenuTree> menuList = remRepeatByMenuId(sysMenuDao.queryMenuTreeByRoleIds(roleIds, false));
            rootMenu = getRootMenu(menuList);
        } catch (Exception e) {
            log.error("queryMenuTreeByUserId失败，原因{}", e);
        }
        return rootMenu;
    }

    @Override
    public List<MenuTree> queryMenuInfosByRoleId(String roleId) {
        List<MenuTree> rootMenu = new ArrayList<>();
        try {
            //查询菜单列表
            List<MenuTree> allMenuList = sysMenuDao.queryMenuTree();
            //查询角色下的菜单
            List<MenuTree> menuList = sysMenuDao.queryMenuTreeByRoleIds(Collections.singletonList(roleId), true);
            for (MenuTree menuTree : allMenuList) {
                for (MenuTree menu : menuList) {
                    if (menuTree.getId().equals(menu.getId())) {
                        menuTree.setState(new State(true));
                    }
                }
                menuTree.setText(menuTree.getMenuName());
            }

            rootMenu = getRootMenu(allMenuList);
        } catch (Exception e) {
            log.error("queryMenuInfosByRoleId失败，原因{}", e);
        }
        return rootMenu;
    }

    @Override
    public List<MenuTree> queryMenuTree() {
        List<MenuTree> menuList = null;
        try {
            //查询菜单列表
            menuList = sysMenuDao.queryMenuTree();
        } catch (Exception e) {
            log.error("queryMenuTree失败，原因{}", e);
        }
        return menuList;
    }

    @Override
    public Integer saveOrdDelMenuInfo(SysMenu menuInfo, SysUser currentUser) {
        Integer count = null;
        Date date = new Date();
        try {
            if (StringUtils.isEmpty(menuInfo.getId())) {
                if (StringUtils.isEmpty(menuInfo.getParentId())) {
                    menuInfo.setParentId("0");
                }
                menuInfo.setId(CommonUtil.getUUID());
                menuInfo.setSfyx(CommonConstants.SFYX_TRUE);
                menuInfo.setCreateUserId(currentUser.getId());
                menuInfo.setCreateTime(date);
                menuInfo.setLastUpdateUserId(currentUser.getId());
                menuInfo.setLastUpdateTime(date);
                count = sysMenuMapper.insertSelective(menuInfo);
            } else {
                //删除菜单信息
                menuInfo.setSfyx(CommonConstants.SFYX_FALSE);
                menuInfo.setLastUpdateUserId(currentUser.getId());
                menuInfo.setLastUpdateTime(date);
                count = sysMenuMapper.updateByPrimaryKeySelective(menuInfo);
                //删除子菜单信息
                SysMenu children = new SysMenu();
                children.setParentId(menuInfo.getId());
                children.setLastUpdateUserId(currentUser.getId());
                children.setLastUpdateTime(date);
                children.setSfyx(CommonConstants.SFYX_FALSE);
                count += sysMenuDao.delChildrenByParentId(children);
            }
        } catch (Exception e) {
            log.error("saveOrdDelMenuInfo失败，原因{}", e);
        }
        return count;
    }

    @Override
    public SysMenu queryMenuInfoById(String menuId) {
        SysMenu menuInfo = null;
        try {
            menuInfo = sysMenuMapper.selectByPrimaryKey(menuId);
        } catch (Exception e){
            log.error("queryMenuInfoById失败，原因{}", e);
        }
        return menuInfo;
    }

    @Override
    public Integer updMenuInfo(SysMenu menuInfo, SysUser currentUser) {
        Integer count = null;
        try {
            menuInfo.setLastUpdateUserId(currentUser.getId());
            menuInfo.setLastUpdateTime(new Date());
            count = sysMenuMapper.updateByPrimaryKeySelective(menuInfo);
        } catch (Exception e) {
            log.error("updMenuInfo失败，原因{}", e);
        }
        return count;
    }

    /**
     * 获取菜单树
     * @param menuList
     * @return List<MenuTree>
     */
    private List<MenuTree> getRootMenu(List<MenuTree> menuList) {
        if (CollectionUtils.isEmpty(menuList)) return null;
        List<MenuTree> rootMenu = new ArrayList<>();
        for (MenuTree menu : menuList) {
            if ("0".equals(menu.getParentId())) {   //父节点是0的，为根节点。
                rootMenu.add(menu);
            }
        }

        for (MenuTree menu : rootMenu) {
            /* 获取根节点下的所有子节点 使用getChild方法*/
            List<MenuTree> childList = getChild(menu.getId(), menuList);
            menu.setNodes(childList);//给根节点设置子节点
        }
        return rootMenu;
    }

    /**
     * 获取子节点
     * @param id
     * @param menuList
     * @return List<MenuTree>
     */
    private List<MenuTree> getChild(String id, List<MenuTree> menuList) {
        //子菜单
        List<MenuTree> childList = new ArrayList<>();
        for (MenuTree menu : menuList) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            if (menu.getParentId().equals(id)) {
                childList.add(menu);
            }
        }

        //递归
        for (MenuTree menu : childList) {
            menu.setNodes(getChild(menu.getId(), menuList));
        }

        //如果节点下没有子节点，返回一个null（递归退出）
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

    /**
     * 根据菜单ID去重
     * @param menuList
     * @return List<MenuTree>
     */
    private List<MenuTree> remRepeatByMenuId(List<MenuTree> menuList) {
        if (CollectionUtils.isEmpty(menuList)) {
            return new ArrayList<>();
        }
        Map<String, Object> menuMap = new HashMap<>();
        Iterator<MenuTree> iterator = menuList.iterator();
        while (iterator.hasNext()) {
            MenuTree menu = iterator.next();
            if (menuMap.get(menu.getId()) != null) {
                iterator.remove();
                continue;
            }
            menuMap.put(menu.getId(), menu);
        }
        return menuList;
    }
}
