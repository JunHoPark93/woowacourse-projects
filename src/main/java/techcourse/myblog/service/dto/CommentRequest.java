package techcourse.myblog.service.dto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class CommentRequest {
    @NotBlank(message = "댓글을 입력하세요!")
    private String contents;
    private Long articleId;
    private LocalDateTime lastCommentCreatedDate;

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

    public LocalDateTime getLastCommentCreatedDate() {
        return lastCommentCreatedDate;
    }

    public void setLastCommentCreatedDate(LocalDateTime lastCommentCreatedDate) {
        this.lastCommentCreatedDate = lastCommentCreatedDate;
    }
}
