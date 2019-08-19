package com.woowacourse.zzazanstagram.model.article.dto;

import javax.validation.constraints.NotBlank;

public class ArticleRequest {

    @NotBlank
    private String image;

    private String contents;
    private String hashTag;

    public ArticleRequest() {
    }

    public ArticleRequest(String image, String contents, String hashTag) {
        this.image = image;
        this.contents = contents;
        this.hashTag = hashTag;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getHashTag() {
        return hashTag;
    }

    public void setHashTag(String hashTag) {
        this.hashTag = hashTag;
    }
}
