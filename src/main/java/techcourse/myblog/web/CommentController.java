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

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<List<CommentResponse>> saveComment(@RequestBody CommentRequest commentRequest, User user) {
        List<CommentResponse> commentResponses = commentService.saveAndGetComments(commentRequest, user);
        return new ResponseEntity<>(commentResponses, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<List<CommentResponse>> deleteComment(@PathVariable("commentId") Long commentId, User user) {
        List<CommentResponse> commentResponses = commentService.deleteAndGetComments(commentId, user);
        return new ResponseEntity<>(commentResponses, HttpStatus.OK);
    }
}

