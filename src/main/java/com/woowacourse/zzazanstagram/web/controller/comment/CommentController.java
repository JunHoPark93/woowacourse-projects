package com.woowacourse.zzazanstagram.web.controller.comment;

import com.woowacourse.zzazanstagram.model.comment.domain.vo.CommentContents;
import com.woowacourse.zzazanstagram.model.comment.dto.CommentResponse;
import com.woowacourse.zzazanstagram.model.comment.service.CommentService;
import com.woowacourse.zzazanstagram.model.member.MemberSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{articleId}/comments/new")
    public ResponseEntity<CommentResponse> createComment(@PathVariable Long articleId, @Valid @RequestBody CommentContents commentContents,
                                                         MemberSession memberSession) {
        CommentResponse commentResponse = commentService.save(commentContents, articleId, memberSession.getEmail());
        return ResponseEntity.ok(commentResponse);
    }
}
