package com.example.demo.service.impl;

import com.alibaba.druid.util.Utils;
import com.example.demo.dao.SysMenuDao;
import com.example.demo.entity.SysMenu;
import com.example.demo.service.SysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>类名称：<b/>SysMenuServiceImpl <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年7月21日 10:25 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    private SysMenuDao menuDao;

    @Override
    public List<SysMenu> findSysMenuByRoleId(Integer rid) {
        List<SysMenu> menuList = menuDao.findSysMenuByRoleId(rid);
        return buildMenuTree(menuList, 0);
    }

    /**
     * 构建菜单树
     * @param menuList
     * @param pid
     * @return List<SysMenu>
     */
    private List<SysMenu> buildMenuTree(List<SysMenu> menuList, Integer pid) {
        List<SysMenu> treeList = new ArrayList<>();
        for (SysMenu menu : menuList) {
            if (menu.getParentId().equals(pid)) {
                menu.setChildren(buildMenuTree(menuList, menu.getId()));
                treeList.add(menu);
            }
        }
        return treeList;
    }
}
