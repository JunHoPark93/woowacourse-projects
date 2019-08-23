package com.woowacourse.zzazanstagram.model.article.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contents contents1 = (Contents) o;
        return Objects.equals(contents, contents1.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contents);
    }
}