package com.woowacourse.zzazanstagram.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.woowacourse.zzazanstagram.web.SessionKeys.MEMBER;

public class GuestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();

        if (httpSession.getAttribute(MEMBER) != null) {
            response.sendRedirect("/");
            return false;
        }

        return true;
    }
}
