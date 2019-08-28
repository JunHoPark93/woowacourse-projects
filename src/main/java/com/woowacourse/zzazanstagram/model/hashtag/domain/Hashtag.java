package com.woowacourse.zzazanstagram.model.hashtag.domain;

import com.woowacourse.zzazanstagram.model.common.BaseEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Hashtag extends BaseEntity {
    @NotNull
    private String keyword;

    protected Hashtag() {
    }

    public Hashtag(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }
}
