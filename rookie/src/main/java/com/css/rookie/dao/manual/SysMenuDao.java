package com.css.rookie.dao.manual;

import com.css.rookie.entity.SysMenu;
import com.css.rookie.vo.MenuTree;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysMenuDao {

    /**
     * 根绝角色信息查询菜单列表
     * @param roleIds
     * @param allShow
     * @return List<MenuTree>
     */
    List<MenuTree> queryMenuTreeByRoleIds(@Param("roleIds") List<String> roleIds, @Param("allShow") boolean allShow);

    /**
     * 菜单树
     * @return List<MenuTree>
     */
    List<MenuTree> queryMenuTree();

    /**
     * 删除子菜单
     * @param menuInfo
     * @return Integer
     */
    Integer delChildrenByParentId(SysMenu menuInfo);

}
