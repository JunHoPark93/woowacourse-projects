package techcourse.myblog.support.auth;

import techcourse.myblog.domain.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserSessionContextHolder {
    private static Map<UserAuthentication, User> context = new ConcurrentHashMap<>();

    public static void bindContext(UserAuthentication authentication, User user) {
        context.put(authentication, user);
    }

    public static User getUserAuthentication(UserAuthentication authentication) {
        return context.get(authentication);
    }

    public static void removeAuthentication(UserAuthentication authentication) {
        context.remove(authentication);
    }
}
