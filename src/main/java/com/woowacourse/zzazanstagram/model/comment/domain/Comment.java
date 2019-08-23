package com.woowacourse.zzazanstagram.model.comment.domain;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.comment.domain.vo.CommentContents;
import com.woowacourse.zzazanstagram.model.common.BaseEntity;
import com.woowacourse.zzazanstagram.model.member.domain.Member;

import javax.persistence.*;

@Entity
public class Comment extends BaseEntity {
    private CommentContents contents;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "article_id", nullable = false, foreignKey = @ForeignKey(name = "fk_comment_to_article"))
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "commenter_id", nullable = false, foreignKey = @ForeignKey(name = "fk_comment_to_commenter"))
    private Member commenter;

    protected Comment() {
    }

    public Comment(CommentContents contents, Article article, Member commenter) {
        this.contents = contents;
        this.article = article;
        this.commenter = commenter;
    }

    public CommentContents getContents() {
        return contents;
    }

    public String getContentsValue() {
        return contents.getContents();
    }

    public Article getArticle() {
        return article;
    }

    public Member getCommenter() {
        return commenter;
    }

}
