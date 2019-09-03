package com.woowacourse.zzazanstagram.model.ddabong.domain;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.common.BaseEntity;
import com.woowacourse.zzazanstagram.model.member.domain.Member;

import javax.persistence.*;

@Entity
public class Ddabong extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false, foreignKey = @ForeignKey(name = "fk_like_to_article"))
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false, foreignKey = @ForeignKey(name = "fk_like_to_member"))
    private Member member;

    private boolean clicked = true;

    protected Ddabong() {
    }

    public Ddabong(Article article, Member member) {
        this.article = article;
        this.member = member;
    }

    public Ddabong changeClicked() {
        clicked = !clicked;
        return this;
    }

    public boolean matchMember(Member member) {
        return this.member.isSame(member);
    }

    public boolean isClicked() {
        return clicked;
    }

    public Article getArticle() {
        return article;
    }

    public Member getMember() {
        return member;
    }
}