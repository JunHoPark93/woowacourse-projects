package techcourse.myblog.service.dto.response;

import techcourse.myblog.domain.User;

public class ArticleResponse {
    private Long id;
    private String title;
    private String coverUrl;
    private String contents;
    private User author;

    private ArticleResponse() {
    }

    public ArticleResponse(Long id, String title, String coverUrl, String contents, User author) {
        this.id = id;
        this.title = title;
        this.coverUrl = coverUrl;
        this.contents = contents;
        this.author = author;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
