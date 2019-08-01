package techcourse.myblog.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import techcourse.myblog.domain.User;
import techcourse.myblog.support.auth.UserAuthentication;
import techcourse.myblog.support.auth.UserSessionContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthInterceptor implements HandlerInterceptor {
    private UserAuthentication userAuthentication;

    public AuthInterceptor(UserAuthentication userAuthentication) {
        this.userAuthentication = userAuthentication;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (isSignUpRequest(request)) {
            return true;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("/login");
            return false;
        }

        bindUserToContext(session, user);
        return true;
    }

    private boolean isSignUpRequest(HttpServletRequest request) {
        return request.getRequestURI().equals("/users") && request.getMethod().equals("POST");
    }

    private void bindUserToContext(HttpSession session, User user) {
        userAuthentication.setSessionId(session.getId());
        UserSessionContextHolder.bindContext(userAuthentication, user);
    }
}
