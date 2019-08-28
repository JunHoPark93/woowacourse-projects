package com.woowacourse.zzazanstagram.model.article.dto;

import org.springframework.web.multipart.MultipartFile;

public class ArticleRequest {

    private MultipartFile file;
    private String contents;
    private String hashTag;

    private ArticleRequest() {
    }

    public ArticleRequest(MultipartFile file, String contents, String hashTag) {
        this.file = file;
        this.contents = contents;
        this.hashTag = hashTag;
    }

    public String getContents() {
        return contents;
    }

    public String getHashTag() {
        return hashTag;
    }

    public MultipartFile getFile() {
        return file;
    }
}
