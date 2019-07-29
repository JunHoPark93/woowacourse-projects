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
    private User commentor;

    @ManyToOne
    private Article article;

    private Comment() {
    }

    public Comment(String contents, Article article, User user) {
        checkContents(contents);
        this.contents = contents;
        this.article = article;
        this.commentor = user;
        this.createdTime = LocalDateTime.now();
    }

    private void checkContents(String contents) {
        if (contents == null) {
            throw new IllegalArgumentException("본문이 없습니다");
        }
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public User getCommentor() {
        return commentor;
    }

    public Article getArticle() {
        return article;
    }
}
