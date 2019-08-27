package com.woowacourse.zzazanstagram.model.hashtag.domain;

import com.woowacourse.zzazanstagram.model.common.BaseEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class TagKeyword extends BaseEntity {
    @NotNull
    private String tagKeyword;

    protected TagKeyword() {
    }

    public TagKeyword(String tagKeyword) {
        this.tagKeyword = tagKeyword;
    }

    public String getTagKeyword() {
        return tagKeyword;
    }
}
