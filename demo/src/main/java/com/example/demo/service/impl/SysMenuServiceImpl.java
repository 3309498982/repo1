package com.example.demo.service.impl;

import com.example.demo.dao.SysMenuDao;
import com.example.demo.entity.SysMenu;
import com.example.demo.entity.SysRole;
import com.example.demo.service.SysMenuService;
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
    public List<SysMenu> findSysMenuByRole(List<SysRole> roles) {
        //查询所有菜单
        List<SysMenu> menuList = menuDao.findSysMenuByRole(roles);
        //根节点
        List<SysMenu> rootMenu = new ArrayList<>();
        for (SysMenu menu : menuList) {
            if (menu.getParentId().equals(0)) {   //父节点是0的，为根节点。
                rootMenu.add(menu);
            }
        }

        for (SysMenu menu : rootMenu) {
            /* 获取根节点下的所有子节点 使用getChild方法*/
            List<SysMenu> childList = getChild(menu.getId(), menuList);
            menu.setChildren(childList);//给根节点设置子节点
        }
        return rootMenu;
    }

    /**
     * 获取子节点
     * @param id       父节点id
     * @param menuList 所有菜单列表
     * @return 每个根节点下，所有子菜单列表
     */
    public List<SysMenu> getChild(Integer id, List<SysMenu> menuList) {
        //子菜单
        List<SysMenu> childList = new ArrayList<>();
        for (SysMenu menu : menuList) {
            // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点。
            if (menu.getParentId().equals(id)) {
                childList.add(menu);
            }
        }
        //递归
        for (SysMenu menu : childList) {
            menu.setChildren(getChild(menu.getId(), menuList));
        }
        //如果节点下没有子节点，返回一个空List（递归退出）
        if (childList.size() == 0) {
            return new ArrayList<>();
        }
        return childList;
    }

}
