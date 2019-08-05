package techcourse.myblog;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import techcourse.myblog.domain.Article;
import techcourse.myblog.domain.User;
import techcourse.myblog.service.ArticleService;
import techcourse.myblog.service.CommentService;
import techcourse.myblog.service.UserService;
import techcourse.myblog.service.dto.ArticleRequest;
import techcourse.myblog.service.dto.CommentRequest;
import techcourse.myblog.service.dto.UserRequest;

@Component
public class Runner implements ApplicationRunner {
    private UserService userService;
    private ArticleService articleService;
    private CommentService commentService;

    public Runner(UserService userService, ArticleService articleService, CommentService commentService) {
        this.userService = userService;
        this.articleService = articleService;
        this.commentService = commentService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setName("CU");
        userRequest.setEmail("root@gmail.com");
        userRequest.setPassword("Password!1");
        userRequest.setReconfirmPassword("Password!1");
        User user = userService.saveUser(userRequest);

        ArticleRequest articleRequest = new ArticleRequest("dfd", "http://woowabros.github.io/img/2019-02-08/techcourse_poster.jpeg", "dd");
        Article article  = articleService.save(articleRequest, user);

        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setArticleId(1L);
        commentRequest.setContents("df");
        commentRequest.setLastCommentCreatedDate(null);

        commentService.save(commentRequest, article, user);
    }
}
