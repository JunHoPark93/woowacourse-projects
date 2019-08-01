package techcourse.myblog.domain;

import techcourse.myblog.domain.common.ContentsAudit;

import javax.persistence.*;

@Entity
public class Article extends ContentsAudit {
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String coverUrl;

    @Column(nullable = false)
    @Lob
    private String contents;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_article_to_user"))
    private User author;

    private Article() {
    }

    public Article(String title, String coverUrl, String contents) {
        this.title = title;
        this.coverUrl = coverUrl;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public String getContents() {
        return contents;
    }

    public void updateArticle(Article article) {
        this.title = article.title;
        this.coverUrl = article.coverUrl;
        this.contents = article.contents;
    }

    public void setAuthor(User user) {
        this.author = user;
    }

    public boolean isAuthor(User author) {
        return this.author.equals(author);
    }
}
