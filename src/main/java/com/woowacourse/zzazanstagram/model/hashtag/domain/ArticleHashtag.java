package com.woowacourse.zzazanstagram.model.hashtag.domain;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.common.BaseEntity;

import javax.persistence.*;

@Entity
public class ArticleHashtag extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false, foreignKey = @ForeignKey(name = "fk_articlehashtag_to_article"))
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id", nullable = false, foreignKey = @ForeignKey(name = "fk_articlehashtag_to_hashtag"))
    private Hashtag hashtag;

    protected ArticleHashtag() {
    }

    public ArticleHashtag(Article article, Hashtag hashtag) {
        this.article = article;
        this.hashtag = hashtag;
    }

    public Article getArticle() {
        return article;
    }

    public Hashtag getHashtag() {
        return hashtag;
    }
}
