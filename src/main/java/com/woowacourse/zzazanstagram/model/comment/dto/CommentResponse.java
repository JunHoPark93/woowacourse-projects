package com.woowacourse.zzazanstagram.model.comment.dto;

import java.util.Objects;

public class CommentResponse {
    private String commentContents;
    private Long commentId;
    private String commenterNickName;

    private CommentResponse() {
    }

    public CommentResponse(String commentContents, Long commentId, String commenterNickName) {
        this.commentContents = commentContents;
        this.commentId = commentId;
        this.commenterNickName = commenterNickName;
    }

    public String getCommentContents() {
        return commentContents;
    }

    public void setCommentContents(String commentContents) {
        this.commentContents = commentContents;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getCommenterNickName() {
        return commenterNickName;
    }

    public void setCommenterNickName(String commenterNickName) {
        this.commenterNickName = commenterNickName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentResponse that = (CommentResponse) o;
        return Objects.equals(commentContents, that.commentContents) &&
                Objects.equals(commentId, that.commentId) &&
                Objects.equals(commenterNickName, that.commenterNickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentContents, commentId, commenterNickName);
    }
}
