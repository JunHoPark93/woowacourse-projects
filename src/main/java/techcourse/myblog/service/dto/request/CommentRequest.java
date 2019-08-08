package techcourse.myblog.service.dto.request;

import javax.validation.constraints.NotBlank;

public class CommentRequest {
    @NotBlank(message = "댓글을 입력하세요!")
    private String contents;
    private Long articleId;

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
}
