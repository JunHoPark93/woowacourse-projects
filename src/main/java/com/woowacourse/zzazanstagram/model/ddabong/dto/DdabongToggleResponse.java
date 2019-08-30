package com.woowacourse.zzazanstagram.model.ddabong.dto;

import java.util.Objects;

public class DdabongToggleResponse {
    private long count;
    private boolean isClicked;

    public DdabongToggleResponse(long count, boolean isClicked) {
        this.count = count;
        this.isClicked = isClicked;
    }

    public long getCount() {
        return count;
    }

    public boolean isClicked() {
        return isClicked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DdabongToggleResponse that = (DdabongToggleResponse) o;
        return count == that.count &&
                isClicked == that.isClicked;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, isClicked);
    }
}
