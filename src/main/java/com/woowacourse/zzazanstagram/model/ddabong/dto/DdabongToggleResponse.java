package com.woowacourse.zzazanstagram.model.ddabong.dto;

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
}
