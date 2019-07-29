package techcourse.myblog.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;

    private LocalDateTime createdTime;

    @ManyToOne
    private User commenter;

    @ManyToOne
    private Article article;

    private Comment() {
    }

    public Comment(String contents, Article article, User user) {
        checkContents(contents);
        this.contents = contents;
        this.article = article;
        this.commenter = user;
        this.createdTime = LocalDateTime.now();
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

    public LocalDateTime getCreatedTime() {
        return createdTime;
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
}
