package slipp;

import nextstep.jdbc.JdbcTemplate;
import nextstep.mvc.DispatcherServlet;
import nextstep.mvc.HandlerMapping;
import nextstep.mvc.asis.Controller;
import nextstep.mvc.asis.ForwardController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import slipp.controller.*;
import slipp.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ManualHandlerMapping implements HandlerMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    private Map<String, Controller> mappings = new HashMap<>();

    public void initialize() {
        JdbcTemplate jdbcTemplate = JdbcTemplate.builder()
                .driver("org.h2.Driver")
                .url("jdbc:h2:mem:jwp-framework")
                .userName("sa")
                .password("")
                .build();

        mappings.put("/", new HomeController());
        mappings.put("/users/form", new ForwardController("/user/form.jsp"));
        mappings.put("/users/loginForm", new ForwardController("/user/login.jsp"));
        mappings.put("/users/login", new LoginController(new UserDao(jdbcTemplate)));
        mappings.put("/users/profile", new ProfileController(new UserDao(jdbcTemplate)));
        mappings.put("/users/logout", new LogoutController());
        mappings.put("/users/updateForm", new UpdateFormUserController(new UserDao(jdbcTemplate)));
        mappings.put("/users/update", new UpdateUserController(new UserDao(jdbcTemplate)));

        logger.info("Initialized Request Mapping!");
        mappings.keySet().forEach(path -> {
            logger.info("Path : {}, Controller : {}", path, mappings.get(path).getClass());
        });
    }

    public Controller getHandler(HttpServletRequest request) {
        return mappings.get(request.getRequestURI());
    }

    void put(String url, Controller controller) {
        mappings.put(url, controller);
    }
}
