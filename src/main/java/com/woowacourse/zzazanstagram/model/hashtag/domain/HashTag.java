package com.woowacourse.zzazanstagram.model.hashtag.domain;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.common.BaseEntity;

import javax.persistence.*;

// TODO 클래스 이름 rename
@Entity
public class HashTag extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", foreignKey = @ForeignKey(name = "fk_hashtag_to_article"), nullable = false)
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tagkeyword_id", foreignKey = @ForeignKey(name = "fk_hashtag_to_tagkeyword"), nullable = false)
    private TagKeyword tagKeyword;

    protected HashTag() {
    }

    public HashTag(Article article, TagKeyword tagKeyword) {
        this.article = article;
        this.tagKeyword = tagKeyword;
    }

    public Article getArticle() {
        return article;
    }

    public TagKeyword getTagKeyword() {
        return tagKeyword;
    }
}
