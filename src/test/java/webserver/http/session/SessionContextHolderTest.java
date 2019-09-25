package webserver.http.session;

import model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SessionContextHolderTest {
    private User user = new User("jay", "password", "jay", "jay@gmail.com");

    @Test
    void bind() {
        HttpSession httpSession = HttpSession.newInstance();
        httpSession.setAttribute("user", user);
        SessionContextHolder.bind("user", httpSession);

        assertTrue(SessionContextHolder.isExists("user"));

    }

    @Test
    void remove() {
        HttpSession httpSession = HttpSession.newInstance();
        httpSession.setAttribute("user", user);
        SessionContextHolder.bind("user", httpSession);
        SessionContextHolder.remove("user");

        assertFalse(SessionContextHolder.isExists("user"));
    }
}