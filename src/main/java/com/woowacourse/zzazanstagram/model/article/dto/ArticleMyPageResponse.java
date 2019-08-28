package com.woowacourse.zzazanstagram.model.article.dto;

public class ArticleMyPageResponse {
    private Long id;
    private String image;
    private long ddabongNumber;
    private long commentNumber;

    private ArticleMyPageResponse() {
    }

    public ArticleMyPageResponse(Long id, String image, long ddabongNumber, long commentNumber) {
        this.id = id;
        this.image = image;
        this.ddabongNumber = ddabongNumber;
        this.commentNumber = commentNumber;
    }

    public Long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public long getDdabongNumber() {
        return ddabongNumber;
    }

    public long getCommentNumber() {
        return commentNumber;
    }
}
