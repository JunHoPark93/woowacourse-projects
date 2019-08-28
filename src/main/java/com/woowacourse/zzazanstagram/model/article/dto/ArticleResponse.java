package com.woowacourse.zzazanstagram.model.article.dto;

import com.woowacourse.zzazanstagram.model.comment.dto.CommentResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ArticleResponse {
    private Long id;
    private String image;
    private String contents;
    private String nickName;
    private String profileImage;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private List<CommentResponse> commentResponses;
    private long ddabongCount;
    private boolean isDdabongClicked;

    private ArticleResponse() {
    }

    public ArticleResponse(Long id, String image, String contents, String nickName, String profileImage, LocalDateTime createdDate, LocalDateTime lastModifiedDate, List<CommentResponse> commentResponses, long ddabongCount, boolean isDdabongClicked) {
        this.id = id;
        this.image = image;
        this.contents = contents;
        this.nickName = nickName;
        this.profileImage = profileImage;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.commentResponses = commentResponses;
        this.ddabongCount = ddabongCount;
        this.isDdabongClicked = isDdabongClicked;
    }

    public Long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getContents() {
        return contents;
    }

    public String getNickName() {
        return nickName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public List<CommentResponse> getCommentResponses() {
        return commentResponses;
    }

    public long getDdabongCount() {
        return ddabongCount;
    }

    public boolean isDdabongClicked() {
        return isDdabongClicked;
    }

    public static final class ArticleResponseBuilder {

        private Long id;
        private String image;
        private String contents;
        private String nickName;
        private String profileImage;
        private LocalDateTime createdDate;
        private LocalDateTime lastModifiedDate;
        private List<CommentResponse> commentResponses;
        private long ddabongCount;
        private boolean isDdabongClicked;

        private ArticleResponseBuilder() {
        }

        public static ArticleResponseBuilder anArticleResponse() {
            return new ArticleResponseBuilder();
        }

        public ArticleResponseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ArticleResponseBuilder image(String image) {
            this.image = image;
            return this;
        }

        public ArticleResponseBuilder contents(String contents) {
            this.contents = contents;
            return this;
        }

        public ArticleResponseBuilder nickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public ArticleResponseBuilder profileImage(String profileImage) {
            this.profileImage = profileImage;
            return this;
        }

        public ArticleResponseBuilder createdDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public ArticleResponseBuilder lastModifiedDate(LocalDateTime lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
            return this;
        }

        public ArticleResponseBuilder commentResponses(List<CommentResponse> commentResponses) {
            this.commentResponses = commentResponses;
            return this;
        }

        public ArticleResponseBuilder ddabongCount(long ddabongCount) {
            this.ddabongCount = ddabongCount;
            return this;
        }

        public ArticleResponseBuilder isDdabongClicked(boolean isDdabongClicked) {
            this.isDdabongClicked = isDdabongClicked;
            return this;
        }

        public ArticleResponse build() {
            return new ArticleResponse(id, image, contents, nickName, profileImage, createdDate, lastModifiedDate, commentResponses, ddabongCount, isDdabongClicked);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ArticleResponse that = (ArticleResponse) o;
        return ddabongCount == that.ddabongCount &&
                isDdabongClicked == that.isDdabongClicked &&
                Objects.equals(id, that.id) &&
                Objects.equals(image, that.image) &&
                Objects.equals(contents, that.contents) &&
                Objects.equals(nickName, that.nickName) &&
                Objects.equals(profileImage, that.profileImage) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(lastModifiedDate, that.lastModifiedDate) &&
                Objects.equals(commentResponses, that.commentResponses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, image, contents, nickName, profileImage, createdDate, lastModifiedDate, commentResponses, ddabongCount, isDdabongClicked);
    }
}
