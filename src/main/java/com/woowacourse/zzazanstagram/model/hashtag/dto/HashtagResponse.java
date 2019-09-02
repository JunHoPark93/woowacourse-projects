package com.woowacourse.zzazanstagram.model.hashtag.dto;

import java.util.Objects;

public class HashtagResponse {
    private String keyword;

    private HashtagResponse() {
    }

    public HashtagResponse(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final HashtagResponse that = (HashtagResponse) o;
        return Objects.equals(keyword, that.keyword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyword);
    }
}
