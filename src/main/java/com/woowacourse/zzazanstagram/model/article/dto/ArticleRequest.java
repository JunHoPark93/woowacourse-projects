package com.woowacourse.zzazanstagram.model.article.dto;

import org.springframework.web.multipart.MultipartFile;

public class ArticleRequest {

    private MultipartFile file;
    private String contents;
    private String hashtag;

    public ArticleRequest() {
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
