package com.woowacourse.zzazanstagram.model.hashtag.domain;

import com.woowacourse.zzazanstagram.model.common.BaseEntity;

import javax.persistence.Entity;

@Entity
public class TagKeyword extends BaseEntity {
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
