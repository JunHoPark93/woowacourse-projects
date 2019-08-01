package techcourse.myblog.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import techcourse.myblog.support.auth.UserAuthentication;
import techcourse.myblog.support.auth.UserSessionContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionContextInterceptor implements HandlerInterceptor {
    private UserAuthentication userAuthentication;

    public SessionContextInterceptor(UserAuthentication userAuthentication) {
        this.userAuthentication = userAuthentication;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return !request.getRequestURI().equals("/users") || !request.getMethod().equals("DELETE");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        UserSessionContextHolder.removeAuthentication(userAuthentication);
    }
}
