package com.css.rookie.controller;

import com.css.rookie.annotation.RequiredPermission;
import com.css.rookie.common.CommonConstants;
import com.css.rookie.common.CommonResponse;
import com.css.rookie.common.CommonTabData;
import com.css.rookie.common.log.CSSLogger;
import com.css.rookie.common.log.CSSLoggerFactory;
import com.css.rookie.entity.SysRole;
import com.css.rookie.entity.SysUser;
import com.css.rookie.service.MenuManagerService;
import com.css.rookie.service.RoleManagerService;
import com.css.rookie.util.CreateResponseUtil;
import com.css.rookie.vo.MenuTree;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>类名称：<b/>RoleManagerController <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年9月8日 9:32 <b/>
 * <b>修改备注：<b/><b/>
 * @version 1.0.0 <b/>
 */
@Controller
@RequestMapping("roleManager")
public class RoleManagerController {

    private CSSLogger log = CSSLoggerFactory.getLogger(this.getClass());

    @Autowired
    private HttpSession session;

    @Autowired
    private RoleManagerService roleManagerService;

    @Autowired
    private MenuManagerService menuManagerService;

    /**
     * 角色管理视图
     * @return String
     */
    @RequiredPermission("sys:role:view")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String index() {
        return "roleManager/list";
    }

    /**
     * 角色管理列表
     * @param condition
     * @return CommonResponse
     */
    @RequiredPermission("sys:role:view")
    @RequestMapping(value = "pageList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse pageList(@RequestBody HashMap<String, Object> condition) {
        CommonResponse response = null;
        try {
            // 参数校验
            if (CollectionUtils.isEmpty(condition)) {
                condition = new HashMap<>();
            }
            // 根据条件分页查询角色列表
            CommonTabData commonTabData = roleManagerService.queryPageDataByCondition(condition);
            if (commonTabData != null) {
                response = CreateResponseUtil.createSuccessResponse(commonTabData);
            } else {
                response = CreateResponseUtil.createErrorResponse(CommonConstants.FAILED_CODE, CommonConstants.FALIED_MSG);
            }
        } catch (Exception e) {
            log.error("调用roleManagerService.queryPageDataByCondition", e);
        }
        return response;
    }

    /**
     * 角色新增视图
     * @return String
     */
    @RequiredPermission("sys:role:view")
    @RequestMapping(value = "addView", method = RequestMethod.GET)
    public String addView() {
        return "roleManager/add";
    }

    /**
     * 角色新增
     * @param roleInfo
     * @return CommonResponse
     */
    @RequiredPermission("sys:role:add")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse saveUserInfo(@RequestBody SysRole roleInfo) {
        CommonResponse response = null;
        try {
            // 参数校验
            if (roleInfo == null) {
                return CreateResponseUtil.createErrorResponse(CommonConstants.PARAM_NULL_CODE, CommonConstants.PARAM_NULL_MSG);
            }
            // 获取当前登录用户
            SysUser currentUser = (SysUser) session.getAttribute("currentUser");

            // 角色新增
            Integer count = roleManagerService.saveOrUpdRoleInfo(roleInfo, currentUser);
            if (count != null) {
                response = CreateResponseUtil.createSuccessResponse();
            } else {
                response = CreateResponseUtil.createErrorResponse(CommonConstants.FAILED_CODE, CommonConstants.FALIED_MSG);
            }
        } catch (Exception e) {
            log.error("调用roleManagerService.saveOrUpdUserInfo", e);
        }
        return response;
    }

    /**
     * 查询角色详情
     * @param id
     * @return CommonResponse
     */
    @RequiredPermission("sys:role:view")
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse queryRoleInfo(@RequestParam("id") String id) {
        CommonResponse response = null;
        try {
            // 参数校验
            if (StringUtils.isEmpty(id)) {
                return CreateResponseUtil.createErrorResponse(CommonConstants.PARAM_NULL_CODE, CommonConstants.PARAM_NULL_MSG);
            }

            // 角色详情
            SysRole role = roleManagerService.queryRoleInfoById(id);
            if (role != null) {
                response = CreateResponseUtil.createSuccessResponse(role);
            } else {
                response = CreateResponseUtil.createErrorResponse(CommonConstants.FAILED_CODE, CommonConstants.FALIED_MSG);
            }
        } catch (Exception e) {
            log.error("调用roleManagerService.queryUserInfoById", e);
        }
        return response;
    }

    /**
     * 角色信息修改视图
     * @param id
     * @param model
     * @return String
     */
    @RequiredPermission("sys:role:view")
    @RequestMapping(value = "editView", method = RequestMethod.GET)
    public String editView(String id, Model model) {
        model.addAttribute("id", id);
        return "roleManager/edit";
    }

    /**
     * 角色信息修改
     * @param roleInfo
     * @return CommonResponse
     */
    @RequiredPermission("sys:role:edit")
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse updUserInfo(@RequestBody SysRole roleInfo) {
        CommonResponse response = null;
        try {
            // 参数校验
            if (roleInfo == null) {
                return CreateResponseUtil.createErrorResponse(CommonConstants.PARAM_NULL_CODE, CommonConstants.PARAM_NULL_MSG);
            }
            // 获取当前登录用户
            SysUser currentUser = (SysUser) session.getAttribute("currentUser");

            // 修改角色信息
            Integer count = roleManagerService.saveOrUpdRoleInfo(roleInfo, currentUser);
            if (count != null) {
                response = CreateResponseUtil.createSuccessResponse();
            } else {
                response = CreateResponseUtil.createErrorResponse(CommonConstants.FAILED_CODE, CommonConstants.FALIED_MSG);
            }
        } catch (Exception e) {
            log.error("调用roleManagerService.saveOrUpdUserInfo", e);
        }
        return response;
    }

    /**
     * 角色信息删除
     * @param roleInfo
     * @return CommonResponse
     */
    @RequiredPermission("sys:role:del")
    @RequestMapping(value = "del", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse delRoleInfo(SysRole roleInfo) {
        CommonResponse response = null;
        try {
            // 参数校验
            if (roleInfo == null) {
                return CreateResponseUtil.createErrorResponse(CommonConstants.PARAM_NULL_CODE, CommonConstants.PARAM_NULL_MSG);
            }
            // 获取当前登录用户
            SysUser currentUser = (SysUser) session.getAttribute("currentUser");

            // 删除角色信息
            Integer count = roleManagerService.delRoleInfo(roleInfo, currentUser);
            if (count != null) {
                response = CreateResponseUtil.createSuccessResponse();
            } else {
                response = CreateResponseUtil.createErrorResponse(CommonConstants.FAILED_CODE, CommonConstants.FALIED_MSG);
            }
        } catch (Exception e) {
            log.error("调用roleManagerService.delRoleInfo", e);
        }
        return response;
    }

    /**
     * 菜单分配视图
     * @param roleId
     * @param model
     * @return String
     */
    @RequiredPermission("sys:role:view")
    @RequestMapping(value = "assignView", method = RequestMethod.GET)
    public String assignView(String roleId, Model model) {
        model.addAttribute("roleId", roleId);
        return "roleManager/assign";
    }

    /**
     * 角色菜单详情
     * @param roleId
     * @return CommonResponse
     */
    @RequiredPermission("sys:role:view")
    @RequestMapping(value = "select", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse queryRoleInfosByUserId(String roleId) {
        CommonResponse response = null;
        try {
            // 参数校验
            if (StringUtils.isEmpty(roleId)) {
                return CreateResponseUtil.createErrorResponse(CommonConstants.PARAM_NULL_CODE, CommonConstants.PARAM_NULL_MSG);
            }

            // 查询角色菜单信息
            List<MenuTree> trees = menuManagerService.queryMenuInfosByRoleId(roleId);
            if (trees != null) {
                response = CreateResponseUtil.createSuccessResponse(trees);
            } else {
                response = CreateResponseUtil.createErrorResponse(CommonConstants.FAILED_CODE, CommonConstants.FALIED_MSG);
            }
        } catch (Exception e) {
            log.error("调用userManagerService.queryRoleInfosByUserId", e);
        }
        return response;
    }

    /**
     * 菜单分配
     * @param condition
     * @return CommonResponse
     */
    @RequiredPermission("sys:role:assign")
    @RequestMapping(value = "assign", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse assigningMenus(@RequestBody Map<String, Object> condition) {
        CommonResponse response = null;
        try {
            // 参数校验
            if (CollectionUtils.isEmpty(condition)) {
                return CreateResponseUtil.createErrorResponse(CommonConstants.PARAM_NULL_CODE, CommonConstants.PARAM_NULL_MSG);
            }
            // 获取当前登录用户
            SysUser currentUser = (SysUser) session.getAttribute("currentUser");

            // 菜单分配
            Integer count = roleManagerService.assigningMenusByCondition(condition, currentUser);
            if (count != null) {
                response = CreateResponseUtil.createSuccessResponse();
            } else {
                response = CreateResponseUtil.createErrorResponse(CommonConstants.FAILED_CODE, CommonConstants.FALIED_MSG);
            }
        } catch (Exception e) {
            log.error("调用menuManagerService.assigningMenus", e);
        }
        return response;
    }
}
