package techcourse.myblog.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import techcourse.myblog.domain.Article;
import techcourse.myblog.domain.Comment;
import techcourse.myblog.domain.User;
import techcourse.myblog.service.ArticleService;
import techcourse.myblog.service.CommentService;
import techcourse.myblog.service.dto.CommentRequest;
import techcourse.myblog.service.dto.CommentResponse;

import javax.validation.Valid;
import java.util.List;

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
    @ResponseBody
    public ResponseEntity<List<CommentResponse>> saveComment(@RequestBody CommentRequest commentRequest, User user) {
        Article article = articleService.findById(commentRequest.getArticleId());
        commentService.save(commentRequest, article, user);
        List<CommentResponse> commentResponses = commentService.findByArticle(article);
        return new ResponseEntity<>(commentResponses, HttpStatus.OK);
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
    @ResponseBody
    public ResponseEntity<List<CommentResponse>> deleteComment(@PathVariable("commentId") Long commentId, User user) {
        Comment comment = commentService.findById(commentId);
        commentService.deleteById(commentId, user);
        List<CommentResponse> commentResponses = commentService.findByArticle(comment.getArticle());
        return new ResponseEntity<>(commentResponses, HttpStatus.OK);
    }
}

