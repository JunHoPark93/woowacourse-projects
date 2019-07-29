package techcourse.myblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import techcourse.myblog.domain.Article;
import techcourse.myblog.domain.User;
import techcourse.myblog.service.ArticleService;
import techcourse.myblog.service.CommentService;
import techcourse.myblog.service.dto.CommentRequest;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class CommentController {
    private CommentService commentService;
    private ArticleService articleService;

    public CommentController(CommentService commentService, ArticleService articleService) {
        this.commentService = commentService;
        this.articleService = articleService;
    }

    @PostMapping("/comment")
    public String saveComment(@Valid CommentRequest commentRequest, HttpSession httpSession) {
        Article article = articleService.findById(commentRequest.getArticleId());
        commentService.save(commentRequest, article, (User) httpSession.getAttribute("user"));
        return "redirect:/articles/" + commentRequest.getArticleId();
    }
}
