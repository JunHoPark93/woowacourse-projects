package techcourse.myblog.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import techcourse.myblog.service.dto.UserSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (isSignUpRequest(request)) {
            return true;
        }

        HttpSession session = request.getSession();
        UserSession userSession = (UserSession) session.getAttribute("user");
        if (userSession == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }

    private boolean isSignUpRequest(HttpServletRequest request) {
        return request.getRequestURI().equals("/users") && request.getMethod().equals("POST");
    }
}
