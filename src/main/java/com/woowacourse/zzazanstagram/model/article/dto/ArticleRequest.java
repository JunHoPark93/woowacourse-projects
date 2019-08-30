package com.woowacourse.zzazanstagram.model.article.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public class ArticleRequest {
    @NotNull
    private MultipartFile file;

    private String contents;

    private ArticleRequest() {
    }

    public ArticleRequest(MultipartFile file, String contents) {
        this.file = file;
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
