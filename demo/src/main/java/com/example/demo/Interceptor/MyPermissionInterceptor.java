package com.example.demo.Interceptor;

import com.example.demo.annotation.MyRequiredPermission;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * <b>类名称：<b/>MyPermissionInterceptor <b/>
 * <b>类描述：<b/><b/>
 * <b>创建人：<b/>刘亚州 <b/>
 * <b>修改人：<b/>刘亚州 <b/>
 * <b>修改时间：<b/>2020年7月20日 16:07 <b/>
 * <b>修改备注：<b/><b/>
 *
 * @version 1.0.0 <b/>
 */
public class MyPermissionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法上的注解
            MyRequiredPermission permission = handlerMethod.getMethod().getAnnotation(MyRequiredPermission.class);
            // 如果方法上的注解为空
            if (permission == null) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.print("{\"msg\":\"请求的controller未配置权限，请配置！！！\"}");
                out.flush();
                out.close();
                return false;
            } else if (permission.value().length != 0) {
                // redis或数据库 中获取该用户的权限信息 并判断是否有权限
                Set<String> permissionSet = (Set<String>) request.getSession().getAttribute("userPermissions");
                if (CollectionUtils.isEmpty(permissionSet)) {
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json;charset=UTF-8");
                    PrintWriter out = response.getWriter();
                    out.print("{\"msg\":\"你无此权限！！！\"}");
                    out.flush();
                    out.close();
                    return false;
                }
                return permissionSet.contains(permission.value());
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
