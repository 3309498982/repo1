package com.css.rookie.Interceptor;

import com.css.rookie.annotation.Panel;
import com.css.rookie.annotation.RequiredPermission;
import com.css.rookie.common.log.CSSLogger;
import com.css.rookie.common.log.CSSLoggerFactory;
import com.css.rookie.entity.SysUser;
import com.css.rookie.service.UserManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Set;

/**
 * <b>类名称：<b/>LoginInterceptor <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年8月20日 16:07 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
public class LoginInterceptor implements HandlerInterceptor {

    private CSSLogger log = CSSLoggerFactory.getLogger(this.getClass());

    @Autowired
    private HttpSession session;

    @Autowired
    private UserManagerService userManagerService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 验证权限
        if (this.hasPermission(handler)) {
            return true;
        }
        log.info("无权限");
        //重置response
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        pw.write("无权限");
        pw.flush();
        pw.close();
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 是否有权限
     */
    private boolean hasPermission(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            // 获取方法上的注解
            Panel panel = handlerMethod.getMethod().getAnnotation(Panel.class);
            // 如果方法上的注解为空 则获取类的注解
            if (panel == null) {
                panel = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Panel.class);
            }

            // 如果注解为null, 说明不需要拦截, 直接放过
            if (panel == null) {
                // 判断用户是否登录
                SysUser currentUser = (SysUser) session.getAttribute("currentUser");
                if (currentUser == null) {
                    return false;
                }

                // 获取方法上的注解
                RequiredPermission requiredPermission = handlerMethod.getMethod().getAnnotation(RequiredPermission.class);
                if (requiredPermission == null) {
                    return false;
                }
                // 如果标记了注解，则判断权限
                if (StringUtils.isNotBlank(requiredPermission.value())) {
                    // 获取该用户的权限信息 并判断是否有权限
                    Set<String> permissionSet = userManagerService.getPermissionSet(currentUser.getId());
//                    permissionSet.add("sys:user:detail");
//                    permissionSet.add("sys:menu:detail");
                    if (CollectionUtils.isEmpty(permissionSet)) {
                        return false;
                    }
                    return permissionSet.contains(requiredPermission.value());
                }
            }
        }
        return true;
    }

}
