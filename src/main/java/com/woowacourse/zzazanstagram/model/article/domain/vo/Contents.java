package com.woowacourse.zzazanstagram.model.article.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
public class Contents {

    @Lob
    @Column(name = "contents")
    private String contents;

    private Contents() {
    }

    private Contents(final String contents) {
        this.contents = contents;
    }

    public static Contents of(final String contents) {
        return new Contents(contents);
    }

    public String getContents() {
        return contents;
    }
}