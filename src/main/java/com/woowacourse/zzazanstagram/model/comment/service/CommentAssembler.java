package com.woowacourse.zzazanstagram.model.comment.service;

import com.woowacourse.zzazanstagram.model.comment.domain.Comment;
import com.woowacourse.zzazanstagram.model.comment.dto.CommentResponse;

public class CommentAssembler {
    public static CommentResponse toDto(Comment comment) {
        return new CommentResponse(comment.getContentsValue(), comment.getId(), comment.getCommenter().getNickNameValue());
    }
}
