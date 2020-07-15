package com.example.rbac.controller;

import com.example.rbac.entity.MSysOperator;
import com.example.rbac.entity.MSysPermission;
import com.example.rbac.entity.MSysRole;
import com.example.rbac.entity.vo.Result;
import com.example.rbac.service.OperatorService;
import com.example.rbac.service.PermissionService;
import com.example.rbac.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>类名称：</b>MenuController<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>刘亚州<br/>
 * <b>修改人：</b>刘亚州<br/>
 * <b>修改时间：</b>2020/7/14 9:50<br/>
 * <b>修改备注：</b><br/>
 *
 * @version 1.0.0<br />
 */
@RestController
public class MenuController {

    @Resource
    private OperatorService operatorService;
    
    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    @RequestMapping("menu")
    public Result<List<Object>> permission(@RequestParam(defaultValue = "user1")String username){
        List<Object> data = new ArrayList<>();
        //获取用户信息
        MSysOperator operator = operatorService.findByOperator(username);
        //获取角色信息
        List<MSysRole> roles = roleService.findByOperatorRole(operator);
        for (MSysRole role : roles) {
            //获取权限信息
            List<MSysPermission> permissions = permissionService.findByRolePermission(role);
            data.add(permissions);
        }
        return new Result<>(data);
    }

}
