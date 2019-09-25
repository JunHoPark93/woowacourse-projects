package webserver.http.session;

import model.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class HttpSessionTest {
    private User user = new User("jay", "password", "jay", "jay@gmail.com");

    @Test
    void session_저장() {
        HttpSession httpSession = HttpSession.newInstance();
        httpSession.setAttribute("user", user);
        Object savedUser = httpSession.getAttribute("user");

        assertThat((User) savedUser).isEqualTo(user);
    }

    @Test
    void session_삭제() {
        HttpSession httpSession = HttpSession.newInstance();
        httpSession.setAttribute("user", user);
        httpSession.removeAttribute("user");
        Object savedUser = httpSession.getAttribute("user");

        assertNull(savedUser);
    }

    @Test
    void session_전체_삭제() {
        HttpSession httpSession = HttpSession.newInstance();
        httpSession.setAttribute("user", user);
        httpSession.setAttribute("test", "hello");
        httpSession.invalidate();

        assertThat(httpSession.size()).isEqualTo(0);
    }
}