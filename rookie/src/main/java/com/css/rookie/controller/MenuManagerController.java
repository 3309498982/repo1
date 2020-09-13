package com.css.rookie.controller;

import com.css.rookie.annotation.RequiredPermission;
import com.css.rookie.common.CommonConstants;
import com.css.rookie.common.CommonResponse;
import com.css.rookie.common.log.CSSLogger;
import com.css.rookie.common.log.CSSLoggerFactory;
import com.css.rookie.entity.SysMenu;
import com.css.rookie.entity.SysUser;
import com.css.rookie.service.MenuManagerService;
import com.css.rookie.util.CreateResponseUtil;
import com.css.rookie.vo.MenuTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <b>类名称：<b/>MenuManagerController <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年9月10日 19:49 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
@Controller
@RequestMapping("menuManager")
public class MenuManagerController {

    private CSSLogger log = CSSLoggerFactory.getLogger(this.getClass());

    @Autowired
    private HttpSession session;

    @Autowired
    private MenuManagerService menuManagerService;

    /**
     * 菜单管理视图
     * @return String
     */
    @RequiredPermission("sys:menu:view")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String index() {
        return "menuManager/list";
    }

    /**
     * 菜单树
     * @return CommonResponse
     */
    @RequiredPermission("sys:menu:view")
    @RequestMapping("menuTree")
    @ResponseBody
    public CommonResponse queryMenuTree() {
        CommonResponse response = null;
        try {
            // 查询菜单列表
            List<MenuTree> trees = menuManagerService.queryMenuTree();
            if (!CollectionUtils.isEmpty(trees)) {
                response = CreateResponseUtil.createSuccessResponse(trees);
            } else {
                response = CreateResponseUtil.createErrorResponse(CommonConstants.FAILED_CODE, CommonConstants.FALIED_MSG);
            }
        } catch (Exception e) {
            log.error("调用menuManagerService.queryMenuTree", e);
        }
        return response;
    }

    /**
     * 菜单新增视图
     * @return String
     */
    @RequiredPermission("sys:menu:view")
    @RequestMapping(value = "addView", method = RequestMethod.GET)
    public String addView(String menuId, Model model) {
        model.addAttribute("menuId", menuId);
        return "menuManager/add";
    }

    /**
     * 菜单新增
     * @param menuInfo
     * @return CommonResponse
     */
    @RequiredPermission("sys:menu:add")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse saveMenuInfo(@RequestBody SysMenu menuInfo) {
        CommonResponse response = null;
        try {
            // 参数校验
            if (menuInfo == null) {
                return CreateResponseUtil.createErrorResponse(CommonConstants.PARAM_NULL_CODE, CommonConstants.PARAM_NULL_MSG);
            }
            // 获取当前登录用户
            SysUser currentUser = (SysUser) session.getAttribute("currentUser");

            // 菜单新增
            Integer count = menuManagerService.saveOrdDelMenuInfo(menuInfo, currentUser);
            if (count != null) {
                response = CreateResponseUtil.createSuccessResponse();
            } else {
                response = CreateResponseUtil.createErrorResponse(CommonConstants.FAILED_CODE, CommonConstants.FALIED_MSG);
            }
        } catch (Exception e) {
            log.error("调用menuManagerService.saveOrdDelMenuInfo", e);
        }
        return response;
    }

    /**
     * 菜单信息删除
     * @param menuInfo
     * @return CommonResponse
     */
    @RequiredPermission("sys:menu:del")
    @RequestMapping(value = "del", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse delMenuInfo(@RequestBody SysMenu menuInfo) {
        CommonResponse response = null;
        try {
            // 参数校验
            if (menuInfo == null || StringUtils.isEmpty(menuInfo.getId())) {
                return CreateResponseUtil.createErrorResponse(CommonConstants.PARAM_NULL_CODE, CommonConstants.PARAM_NULL_MSG);
            }
            // 获取当前登录用户
            SysUser currentUser = (SysUser) session.getAttribute("currentUser");

            // 菜单删除
            Integer count = menuManagerService.saveOrdDelMenuInfo(menuInfo, currentUser);
            if (count != null) {
                response = CreateResponseUtil.createSuccessResponse();
            } else {
                response = CreateResponseUtil.createErrorResponse(CommonConstants.FAILED_CODE, CommonConstants.FALIED_MSG);
            }
        } catch (Exception e) {
            log.error("调用menuManagerService.saveOrdDelMenuInfo", e);
        }
        return response;
    }

    /**
     * 菜单修改视图
     * @return String
     */
    @RequiredPermission("sys:menu:view")
    @RequestMapping(value = "editView", method = RequestMethod.GET)
    public String editView(String menuId, Model model) {
        model.addAttribute("menuId", menuId);
        return "menuManager/edit";
    }

    /**
     * 菜单详情
     * @param menuId
     * @return CommonResponse
     */
    @RequiredPermission("sys:menu:view")
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse queryMenuInfo(@RequestParam("menuId") String menuId) {
        CommonResponse response = null;
        try {
            // 参数校验
            if (StringUtils.isEmpty(menuId)) {
                return CreateResponseUtil.createErrorResponse(CommonConstants.PARAM_NULL_CODE, CommonConstants.PARAM_NULL_MSG);
            }

            // 菜单详情
            SysMenu menuInfo = menuManagerService.queryMenuInfoById(menuId);
            if (menuInfo != null) {
                response = CreateResponseUtil.createSuccessResponse(menuInfo);
            } else {
                response = CreateResponseUtil.createErrorResponse(CommonConstants.FAILED_CODE, CommonConstants.FALIED_MSG);
            }
        } catch (Exception e) {
            log.error("调用menuManagerService.queryMenuInfoById", e);
        }
        return response;
    }

    /**
     * 菜单修改
     * @param menuInfo
     * @return CommonResponse
     */
    @RequiredPermission("sys:menu:edit")
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse updMenuInfo(@RequestBody SysMenu menuInfo) {
        CommonResponse response = null;
        try {
            // 参数校验
            if (menuInfo == null) {
                return CreateResponseUtil.createErrorResponse(CommonConstants.PARAM_NULL_CODE, CommonConstants.PARAM_NULL_MSG);
            }
            // 获取当前登录用户
            SysUser currentUser = (SysUser) session.getAttribute("currentUser");

            // 菜单更新
            Integer count = menuManagerService.updMenuInfo(menuInfo, currentUser);
            if (count != null) {
                response = CreateResponseUtil.createSuccessResponse();
            } else {
                response = CreateResponseUtil.createErrorResponse(CommonConstants.FAILED_CODE, CommonConstants.FALIED_MSG);
            }
        } catch (Exception e) {
            log.error("调用menuManagerService.updMenuInfo", e);
        }
        return response;
    }
}
