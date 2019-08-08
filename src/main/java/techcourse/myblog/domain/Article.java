package techcourse.myblog.domain;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import techcourse.myblog.domain.common.ContentsAudit;
import techcourse.myblog.service.exception.ResourceNotFoundException;

import javax.persistence.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Entity
@EntityListeners(value = AuditingEntityListener.class)
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
        checkCoverImageResource(coverUrl);
        this.title = title;
        this.coverUrl = coverUrl;
        this.contents = contents;
    }

    private void checkCoverImageResource(String coverUrl) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection con = (HttpURLConnection) new URL(coverUrl).openConnection();
            con.setRequestMethod("HEAD");
            if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new ResourceNotFoundException("리소스가 존재하지 않습니다");
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException("리소스 경로가 올바르지 않습니다");
        }
    }

    public Long getId() {
        return id;
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

    public User getAuthor() {
        return author;
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
