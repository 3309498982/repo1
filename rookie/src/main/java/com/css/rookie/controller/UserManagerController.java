package com.css.rookie.controller;

import com.css.rookie.annotation.RequiredPermission;
import com.css.rookie.common.CommonConstants;
import com.css.rookie.common.CommonResponse;
import com.css.rookie.common.CommonTabData;
import com.css.rookie.common.log.CSSLogger;
import com.css.rookie.common.log.CSSLoggerFactory;
import com.css.rookie.entity.SysUser;
import com.css.rookie.service.UserManagerService;
import com.css.rookie.util.CreateResponseUtil;
import com.css.rookie.vo.SysRoleVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * <b>类名称：<b/>UserManagerController <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年9月8日 9:32 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
@Controller
@RequestMapping("userManager")
public class UserManagerController {

    private CSSLogger log = CSSLoggerFactory.getLogger(this.getClass());

    @Autowired
    private HttpSession session;

    @Autowired
    private UserManagerService userManagerService;

    /**
     * 用户管理视图
     * @return String
     */
    @RequiredPermission("sys:user:view")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String index() {
        return "userManager/list";
    }

    /**
     * 用户管理列表
     * @param condition
     * @return CommonResponse
     */
    @RequiredPermission("sys:user:view")
    @RequestMapping(value = "pageList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse pageList(@RequestBody HashMap<String, Object> condition) {
        CommonResponse response = null;
        try {
            // 参数校验
            if (CollectionUtils.isEmpty(condition)) {
                condition = new HashMap<>();
            }
            // 根据条件分页查询用户列表
            CommonTabData commonTabData = userManagerService.queryPageDataByCondition(condition);

            if (commonTabData != null) {
                response = CreateResponseUtil.createSuccessResponse(commonTabData);
            } else {
                response = CreateResponseUtil.createErrorResponse(CommonConstants.FAILED_CODE, CommonConstants.FALIED_MSG);
            }
        } catch (Exception e) {
            log.error("调用userManagerService.queryPageDataByCondition", e);
        }
        return response;
    }

    /**
     * 用户新增视图
     * @return String
     */
    @RequiredPermission("sys:user:view")
    @RequestMapping(value = "addView", method = RequestMethod.GET)
    public String addView() {
        return "userManager/add";
    }

    /**
     * 用户新增
     * @param userInfo
     * @return CommonResponse
     */
    @RequiredPermission("sys:user:add")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse saveUserInfo(@RequestBody SysUser userInfo) {
        CommonResponse response = null;
        try {
            // 参数校验
            if (userInfo == null) {
                return CreateResponseUtil.createErrorResponse(CommonConstants.PARAM_NULL_CODE, CommonConstants.PARAM_NULL_MSG);
            }
            // 获取当前登录用户
            SysUser currentUser = (SysUser) session.getAttribute("currentUser");

            // 用户新增
            Integer count = userManagerService.saveOrUpdUserInfo(userInfo, currentUser);
            if (count != null) {
                response = CreateResponseUtil.createSuccessResponse();
            } else {
                response = CreateResponseUtil.createErrorResponse(CommonConstants.FAILED_CODE, CommonConstants.FALIED_MSG);
            }
        } catch (Exception e) {
            log.error("调用userManagerService.saveOrUpdUserInfo", e);
        }
        return response;
    }

    /**
     * 查询用户详情
     * @param id
     * @return CommonResponse
     */
    @RequiredPermission("sys:user:view")
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse queryUserInfo(@RequestParam("id") String id) {
        CommonResponse response = null;
        try {
            // 参数校验
            if (StringUtils.isEmpty(id)) {
                return CreateResponseUtil.createErrorResponse(CommonConstants.PARAM_NULL_CODE, CommonConstants.PARAM_NULL_MSG);
            }

            // 用户详情
            SysUser user = userManagerService.queryUserInfoById(id);
            if (user != null) {
                response = CreateResponseUtil.createSuccessResponse(user);
            } else {
                response = CreateResponseUtil.createErrorResponse(CommonConstants.FAILED_CODE, CommonConstants.FALIED_MSG);
            }
        } catch (Exception e) {
            log.error("调用userManagerService.queryUserInfoById", e);
        }
        return response;
    }

    /**
     * 用户信息修改视图
     * @param id
     * @param model
     * @return String
     */
    @RequiredPermission("sys:user:view")
    @RequestMapping(value = "editView", method = RequestMethod.GET)
    public String editView(String id, Model model) {
        model.addAttribute("id", id);
        return "userManager/edit";
    }

    /**
     * 用户信息修改
     * @param userInfo
     * @return CommonResponse
     */
    @RequiredPermission("sys:user:edit")
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse updUserInfo(@RequestBody SysUser userInfo) {
        CommonResponse response = null;
        try {
            // 参数校验
            if (userInfo == null) {
                return CreateResponseUtil.createErrorResponse(CommonConstants.PARAM_NULL_CODE, CommonConstants.PARAM_NULL_MSG);
            }
            // 获取当前登录用户
            SysUser currentUser = (SysUser) session.getAttribute("currentUser");

            // 修改用户信息
            Integer count = userManagerService.saveOrUpdUserInfo(userInfo, currentUser);
            if (count != null) {
                response = CreateResponseUtil.createSuccessResponse();
            } else {
                response = CreateResponseUtil.createErrorResponse(CommonConstants.FAILED_CODE, CommonConstants.FALIED_MSG);
            }
        } catch (Exception e) {
            log.error("调用userManagerService.saveOrUpdUserInfo", e);
        }
        return response;
    }

    /**
     * 用户冻结
     * @param userInfo
     * @return CommonResponse
     */
    @RequiredPermission("sys:user:freeze")
    @RequestMapping(value = "freeze", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse freezeUser(@RequestBody SysUser userInfo) {
        CommonResponse response = null;
        try {
            // 参数校验
            if (userInfo == null) {
                return CreateResponseUtil.createErrorResponse(CommonConstants.PARAM_NULL_CODE, CommonConstants.PARAM_NULL_MSG);
            }
            // 获取当前登录用户
            SysUser currentUser = (SysUser) session.getAttribute("currentUser");

            // 修改用户信息
            Integer count = userManagerService.saveOrUpdUserInfo(userInfo, currentUser);
            if (count != null) {
                // 更新session
                session.setAttribute("currentUser", userManagerService.queryUserInfoById(currentUser.getId()));
                response = CreateResponseUtil.createSuccessResponse();
            } else {
                response = CreateResponseUtil.createErrorResponse(CommonConstants.FAILED_CODE, CommonConstants.FALIED_MSG);
            }
        } catch (Exception e) {
            log.error("调用userManagerService.freezeOrNotFreezeUser", e);
        }
        return response;
    }

    /**
     * 用户信息删除
     * @param userInfo
     * @return CommonResponse
     */
    @RequiredPermission("sys:user:del")
    @RequestMapping(value = "del", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse delUserInfo(SysUser userInfo) {
        CommonResponse response = null;
        try {
            // 参数校验
            if (userInfo == null) {
                return CreateResponseUtil.createErrorResponse(CommonConstants.PARAM_NULL_CODE, CommonConstants.PARAM_NULL_MSG);
            }
            // 获取当前登录用户
            SysUser currentUser = (SysUser) session.getAttribute("currentUser");

            // 修改用户信息
            Integer count = userManagerService.delUserInfo(userInfo, currentUser);
            if (count != null) {
                response = CreateResponseUtil.createSuccessResponse();
            } else {
                response = CreateResponseUtil.createErrorResponse(CommonConstants.FAILED_CODE, CommonConstants.FALIED_MSG);
            }
        } catch (Exception e) {
            log.error("调用userManagerService.delUserInfo", e);
        }
        return response;
    }

    /**
     * 角色分配视图
     * @param id
     * @param model
     * @return String
     */
    @RequiredPermission("sys:user:view")
    @RequestMapping(value = "assignView", method = RequestMethod.GET)
    public String assignView(String id, Model model) {
        model.addAttribute("id", id);
        return "userManager/assign";
    }

    /**
     * 用户角色详情
     * @param id
     * @return CommonResponse
     */
    @RequiredPermission("sys:user:view")
    @RequestMapping(value = "select", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse queryRoleInfosByUserId(String id) {
        CommonResponse response = null;
        try {
            // 参数校验
            if (StringUtils.isEmpty(id)) {
                return CreateResponseUtil.createErrorResponse(CommonConstants.PARAM_NULL_CODE, CommonConstants.PARAM_NULL_MSG);
            }

            // 查询用户角色信息
            List<SysRoleVo> sysRoleVos = userManagerService.queryRoleInfosByUserId(id);
            if (sysRoleVos != null) {
                response = CreateResponseUtil.createSuccessResponse(sysRoleVos);
            } else {
                response = CreateResponseUtil.createErrorResponse(CommonConstants.FAILED_CODE, CommonConstants.FALIED_MSG);
            }
        } catch (Exception e) {
            log.error("调用userManagerService.queryRoleInfosByUserId", e);
        }
        return response;
    }

    /**
     * 角色分配
     * @param condition
     * @return CommonResponse
     */
    @RequiredPermission("sys:user:assign")
    @RequestMapping(value = "assign", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse assigningRoles(@RequestBody HashMap<String, Object> condition) {
        CommonResponse response = null;
        try {
            // 参数校验
            if (CollectionUtils.isEmpty(condition)) {
                return CreateResponseUtil.createErrorResponse(CommonConstants.PARAM_NULL_CODE, CommonConstants.PARAM_NULL_MSG);
            }
            // 获取当前登录用户
            SysUser currentUser = (SysUser) session.getAttribute("currentUser");

            // 角色分配
            Integer count = userManagerService.assigningRoles(condition, currentUser);
            if (count != null) {
                response = CreateResponseUtil.createSuccessResponse();
            } else {
                response = CreateResponseUtil.createErrorResponse(CommonConstants.FAILED_CODE, CommonConstants.FALIED_MSG);
            }
        } catch (Exception e) {
            log.error("调用userManagerService.assigningRoles", e);
        }
        return response;
    }
}
