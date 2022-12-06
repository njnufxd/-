package com.company.demo.interceptor;

import com.company.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 用户信息拦截器
 */
public class UserInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInterceptor.class);
    // Controller方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        if (session==null){
            String loginPageUrl = "/login";
            response.sendRedirect(loginPageUrl);
            return false;
        }
        Long userId= (Long) session.getAttribute("userId");
        if (userId!=null){
            return true;
        }
        String loginPageUrl = "/login";
        response.sendRedirect(loginPageUrl);
        return true;
    }

    //Controller方法执行之后
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    // 整个请求完成后（包括Thymeleaf渲染完毕）
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
