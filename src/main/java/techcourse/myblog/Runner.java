package techcourse.myblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import techcourse.myblog.domain.User;
import techcourse.myblog.service.ArticleService;
import techcourse.myblog.service.UserService;
import techcourse.myblog.service.dto.ArticleRequest;
import techcourse.myblog.service.dto.UserRequest;

@Component
public class Runner implements ApplicationRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("CU");
        userRequest.setEmail("root@gmail.com");
        userRequest.setPassword("Password!1");
        userRequest.setReconfirmPassword("Password!1");
        User user = userService.saveUser(userRequest);

        ArticleRequest articleRequest = new ArticleRequest("title", "dd", "Dzz");

        articleService.save(articleRequest, user);
    }
}
