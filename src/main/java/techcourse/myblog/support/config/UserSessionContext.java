package techcourse.myblog.support.config;

import techcourse.myblog.domain.User;

public class UserSessionContext {
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void set(User user) {
        UserSessionContext.user = user;
    }

    public static void remove() {
        UserSessionContext.user = null;
    }
}
