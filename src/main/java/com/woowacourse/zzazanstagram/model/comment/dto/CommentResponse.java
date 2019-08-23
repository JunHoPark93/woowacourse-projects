package com.woowacourse.zzazanstagram.model.comment.dto;

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
}
