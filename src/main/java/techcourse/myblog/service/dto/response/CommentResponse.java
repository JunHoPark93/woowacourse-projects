package techcourse.myblog.service.dto.response;

import techcourse.myblog.domain.Article;
import techcourse.myblog.domain.User;

public class CommentResponse {
    private Long id;
    private String contents;
    private Long elapsedTime;
    private User commenter;
    private Article article;

    private CommentResponse() {
    }

    public CommentResponse(Long id, String contents, Long elapsedTime, User commenter, Article article) {
        this.id = id;
        this.contents = contents;
        this.elapsedTime = elapsedTime;
        this.commenter = commenter;
        this.article = article;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public User getCommenter() {
        return commenter;
    }

    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
