package com.woowacourse.zzazanstagram.model.article.dto;

import org.springframework.web.multipart.MultipartFile;

public class ArticleRequest {

    private MultipartFile file;
    private String contents;
    private String hashtag;

    private ArticleRequest() {
    }

    // TODO : hashtag field 삭제
    public ArticleRequest(MultipartFile file, String contents, String hashTag) {
        this.file = file;
        this.contents = contents;
        this.hashTag = hashTag;
    }

    public String getContents() {
        return contents;
    }

    public String getHashtag() {
        return hashtag;
    }

    public MultipartFile getFile() {
        return file;
    }
}
