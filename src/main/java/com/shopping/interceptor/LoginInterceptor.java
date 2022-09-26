package com.shopping.interceptor;

import com.shopping.controller.MemberSession;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(MemberSession.LOGIN_SESSION) == null) {
            if (request.getQueryString() == null) {
                response.sendRedirect("/members/login?redirectURL=" + request.getRequestURI());
                return false;
            }
            response.sendRedirect("/members/login?redirectURL=" + request.getRequestURI() + "?" +  request.getQueryString());
            return false;
        }

        return true;
    }
}
