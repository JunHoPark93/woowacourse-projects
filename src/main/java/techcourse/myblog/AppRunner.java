package techcourse.myblog;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import techcourse.myblog.service.ArticleService;
import techcourse.myblog.service.UserService;
import techcourse.myblog.service.dto.ArticleRequest;
import techcourse.myblog.service.dto.UserRequest;

@Component
public class AppRunner implements ApplicationRunner {
    private UserService userService;
    private ArticleService articleService;

    public AppRunner(UserService userService, ArticleService articleService) {
        this.userService = userService;
        this.articleService = articleService;
    }

    @Override
    public void run(ApplicationArguments args) {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("CU");
        userRequest.setEmail("root@gmail.com");
        userRequest.setPassword("PassWord!1");
        userRequest.setReconfirmPassword("PassWord!1");

        userService.saveUser(userRequest);

        for (int i = 0; i < 30; i++) {
            articleService.save(new ArticleRequest(i+"'s title", "http://img.hani.co.kr/imgdb/resize/2018/1123/00502680_20181123.JPG", "contentsss"));
        }
    }
}
