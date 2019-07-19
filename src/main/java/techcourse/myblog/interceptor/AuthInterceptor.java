package techcourse.myblog.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import techcourse.myblog.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().equals("/users") && request.getMethod().equals("POST")) {
            return true;
        }

        User userSession = (User) request.getSession().getAttribute("user");

        if (userSession == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
