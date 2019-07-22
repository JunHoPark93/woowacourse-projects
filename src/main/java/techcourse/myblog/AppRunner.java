package techcourse.myblog;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import techcourse.myblog.service.UserService;
import techcourse.myblog.service.dto.UserRequest;

@Component
public class AppRunner implements ApplicationRunner {
    private UserService userService;

    public AppRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("CU");
        userRequest.setEmail("root@gmail.com");
        userRequest.setPassword("PassWord!1");
        userRequest.setReconfirmPassword("PassWord!1");

        userService.saveUser(userRequest);
    }
}
