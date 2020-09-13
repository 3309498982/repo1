package com.css.rookie.controller;

import com.css.rookie.annotation.Panel;
import com.css.rookie.common.BusinessConstants;
import com.css.rookie.common.CommonConstants;
import com.css.rookie.common.CommonResponse;
import com.css.rookie.common.log.CSSLogger;
import com.css.rookie.common.log.CSSLoggerFactory;
import com.css.rookie.entity.SysUser;
import com.css.rookie.service.MenuManagerService;
import com.css.rookie.service.UserManagerService;
import com.css.rookie.util.CreateResponseUtil;
import com.css.rookie.util.ParamVerifyUtil;
import com.css.rookie.vo.MenuTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Panel
public class LoginController {

    private CSSLogger log = CSSLoggerFactory.getLogger(this.getClass());

    @Autowired
    private HttpSession session;

    @Autowired
    private UserManagerService userManagerService;

    @Autowired
    private MenuManagerService menuManagerService;

    /**
     * 登录首页
     * @return String
     */
    @RequestMapping(value = "loginPage", method = RequestMethod.GET)
    public String loginPage() {
        if (session.getAttribute("check_username") != null) {
            return "index";
        }
        return "loginPage";
    }

    /**
     * 审核用户登录信息
     * @param username
     * @param password
     * @return CommonResponse
     */
    @RequestMapping(value = "checkUser", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse checkUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        CommonResponse response = null;
        try {
            // 校验参数
            if (ParamVerifyUtil.isEmpty(username, password)) {
                return CreateResponseUtil.createErrorResponse(CommonConstants.PARAM_NULL_CODE, CommonConstants.PARAM_NULL_MSG);
            }
            // 根据用户名获取用户信息
            SysUser userInfo = userManagerService.queryUserInfoByUsername(username);

            password = DigestUtils.md5DigestAsHex(password.getBytes());
            if (userInfo != null && password.equals(userInfo.getPassword())
                    && BusinessConstants.STATUS_FREEZE_FALSE.equals(userInfo.getIsFreeze())) {
                session.setAttribute("check_username", userInfo.getUsername());
                response = CreateResponseUtil.createSuccessResponse();
            } else {
                response = CreateResponseUtil.createErrorResponse(CommonConstants.FAILED_CODE, CommonConstants.FALIED_MSG);
            }
        } catch (Exception e) {
            log.error("调用userManagerService.queryUserInfoByUsername", e);
        }
        return response;
    }

    /**
     * 用户登录
     * @param model
     * @return String
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model) {
        // 用户是否登录
        SysUser currentUser = (SysUser) session.getAttribute("currentUser");
        if (currentUser != null) {
            model.addAttribute("username", session.getAttribute("login_username"));
            // 用户已登录
            return "index";
        }

        String username = String.valueOf(session.getAttribute("check_username"));
        currentUser = userManagerService.queryUserInfoByUsername(username);
        //获取用户信息 保存到session
        session.setAttribute("login_username", username);
        session.setAttribute("currentUser", currentUser);

        // 查询用户菜单列表
        List<MenuTree> trees = menuManagerService.queryMenuTreeByUserId(currentUser.getId());
        model.addAttribute("trees", trees);
        model.addAttribute("username", session.getAttribute("login_username"));
        return "index";
    }

    /**
     * 用户登出
     * @return String
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        session.invalidate();
        return "loginPage";
    }
}
