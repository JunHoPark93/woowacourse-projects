package com.woowacourse.zzazanstagram.model.article.dto;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ArticleMyPageResponse that = (ArticleMyPageResponse) o;
        return ddabongNumber == that.ddabongNumber &&
                commentNumber == that.commentNumber &&
                Objects.equals(id, that.id) &&
                Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, image, ddabongNumber, commentNumber);
    }
}
