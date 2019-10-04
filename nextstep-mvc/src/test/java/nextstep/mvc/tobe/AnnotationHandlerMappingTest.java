package nextstep.mvc.tobe;

import nextstep.db.DataBase;
import nextstep.mvc.HandlerMapping;
import nextstep.mvc.tobe.adapter.AnnotationHandlerAdapter;
import nextstep.mvc.tobe.adapter.HandlerAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class AnnotationHandlerMappingTest {
    private HandlerMapping handlerMapping;
    private HandlerAdapter handlerAdapter;

    @BeforeEach
    public void setup() {
        handlerMapping = new AnnotationHandlerMapping("nextstep.mvc.tobe");
        handlerMapping.initialize();
        handlerAdapter = new AnnotationHandlerAdapter();
    }

    @Test
    public void create_find() throws Exception {
        User user = new User("pobi", "password", "포비", "pobi@nextstep.camp");
        createUser(user);
        assertThat(DataBase.findUserById(user.getUserId())).isEqualTo(user);

        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/users");
        request.setParameter("userId", user.getUserId());
        MockHttpServletResponse response = new MockHttpServletResponse();
        Object handler = handlerMapping.getHandler(request);
        handlerAdapter.handle(request, response, handler);

        assertThat(request.getAttribute("user")).isEqualTo(user);
    }

    private void createUser(User user) throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/users");
        request.setParameter("userId", user.getUserId());
        request.setParameter("password", user.getPassword());
        request.setParameter("name", user.getName());
        request.setParameter("email", user.getEmail());
        MockHttpServletResponse response = new MockHttpServletResponse();
        Object handler = handlerMapping.getHandler(request);

        handlerAdapter.handle(request, response, handler);
    }
}
