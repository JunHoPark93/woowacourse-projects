package techcourse.myblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import techcourse.myblog.domain.Article;
import techcourse.myblog.domain.User;
import techcourse.myblog.service.ArticleService;
import techcourse.myblog.service.CommentService;
import techcourse.myblog.service.dto.CommentRequest;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/comment")
public class CommentController {
    private CommentService commentService;
    private ArticleService articleService;

    public CommentController(CommentService commentService, ArticleService articleService) {
        this.commentService = commentService;
        this.articleService = articleService;
    }

    @PostMapping
    public String saveComment(@Valid CommentRequest commentRequest, User user) {
        Article article = articleService.findById(commentRequest.getArticleId());
        commentService.save(commentRequest, article, user);
        return "redirect:/articles/" + commentRequest.getArticleId();
    }

    @GetMapping("/{commentId}")
    public String editCommentPage(@PathVariable("commentId") Long commentId, Model model, User user) {
        model.addAttribute("comment",
                commentService.findComment(user, commentId));
        return "mycomment-edit";
    }

    @PutMapping("/{commentId}")
    public String editComment(@Valid CommentRequest commentRequest, @PathVariable("commentId") Long commentId, User user) {
        commentService.update(commentRequest, user, commentId);
        return "redirect:/articles/" + commentRequest.getArticleId();
    }

    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable("commentId") Long commentId, User user) {
        commentService.deleteById(commentId, user);
        return "redirect:/";
    }
}

