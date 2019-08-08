package techcourse.myblog;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import techcourse.myblog.service.UserService;
import techcourse.myblog.service.dto.request.UserRequest;

@Component
public class Runner implements ApplicationRunner {
    private UserService userService;

    public Runner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("CU");
        userRequest.setEmail("root@gmail.com");
        userRequest.setPassword("Password!1");
        userRequest.setReconfirmPassword("Password!1");
        userService.save(userRequest);
    }
}
