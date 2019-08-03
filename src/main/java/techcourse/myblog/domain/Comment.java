package techcourse.myblog.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import techcourse.myblog.domain.common.ContentsAudit;

import javax.persistence.*;

@Entity
@EntityListeners(value = AuditingEntityListener.class)
public class Comment extends ContentsAudit {
    @Lob
    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_comment_to_user"), nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User commenter;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_comment_to_article"), nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Article article;

    private Comment() {
    }

    public Comment(String contents, Article article, User user) {
        checkContents(contents);
        this.contents = contents;
        this.article = article;
        this.commenter = user;
    }

    private void checkContents(String contents) {
        if (contents == null) {
            throw new IllegalArgumentException("본문이 없습니다");
        }
    }

    public Long getId() {
        return id;
    }

    public String getContents() {
        return contents;
    }

    public User getCommenter() {
        return commenter;
    }

    public Article getArticle() {
        return article;
    }

    public Comment updateContents(String contents) {
        this.contents = contents;
        return this;
    }

    public boolean isCommentor(User commenter) {
        return this.commenter.equals(commenter);
    }
}
