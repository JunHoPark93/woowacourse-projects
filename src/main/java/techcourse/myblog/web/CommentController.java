package techcourse.myblog.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techcourse.myblog.domain.Article;
import techcourse.myblog.domain.Comment;
import techcourse.myblog.domain.User;
import techcourse.myblog.service.ArticleService;
import techcourse.myblog.service.CommentService;
import techcourse.myblog.service.dto.CommentRequest;
import techcourse.myblog.service.dto.CommentResponse;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private CommentService commentService;
    private ArticleService articleService;

    public CommentController(CommentService commentService, ArticleService articleService) {
        this.commentService = commentService;
        this.articleService = articleService;
    }

    @PostMapping
    public ResponseEntity<List<CommentResponse>> saveComment(@RequestBody CommentRequest commentRequest, User user) {
        Article article = articleService.findById(commentRequest.getArticleId());
        commentService.save(commentRequest, article, user);
        List<CommentResponse> commentResponses = commentService.findByArticle(article);
        return new ResponseEntity<>(commentResponses, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<List<CommentResponse>> deleteComment(@PathVariable("commentId") Long commentId, User user) {
        Comment comment = commentService.findById(commentId);
        commentService.deleteById(commentId, user);
        List<CommentResponse> commentResponses = commentService.findByArticle(comment.getArticle());
        return new ResponseEntity<>(commentResponses, HttpStatus.OK);
    }
}

