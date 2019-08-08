package techcourse.myblog.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techcourse.myblog.service.CommentService;
import techcourse.myblog.service.dto.UserSession;
import techcourse.myblog.service.dto.request.CommentRequest;
import techcourse.myblog.service.dto.response.CommentResponse;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<List<CommentResponse>> saveComment(@RequestBody CommentRequest commentRequest, UserSession userSession) {
        List<CommentResponse> commentResponses = commentService.saveAndGet(commentRequest, userSession);
        return new ResponseEntity<>(commentResponses, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<List<CommentResponse>> deleteComment(@PathVariable("commentId") Long commentId, UserSession userSession) {
        List<CommentResponse> commentResponses = commentService.deleteAndGet(commentId, userSession);
        return new ResponseEntity<>(commentResponses, HttpStatus.OK);
    }
}

