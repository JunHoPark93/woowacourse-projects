package com.woowacourse.zzazanstagram.model.comment.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import java.util.Objects;

@Embeddable
public class CommentContents {

    @Size(min = 1, max = 500, message = "댓글의 내용은 1글자 이상 500글자 이하만 가능합니다.")
    @Column(name = "contents", length = 500)
    private String contents;

    private CommentContents() {
    }

    private CommentContents(final String contents) {
        this.contents = contents;
    }

    public static CommentContents of(String contents) {
        return new CommentContents(contents);
    }

    public String getContents() {
        return contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentContents that = (CommentContents) o;
        return Objects.equals(contents, that.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contents);
    }
}