package techcourse.myblog.dto;

import techcourse.myblog.validator.TitleConstraint;

public class ArticleDto {
    @TitleConstraint
    private String title;
    private String coverUrl;
    private String contents;

    public ArticleDto(String title, String coverUrl, String contents) {
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
}
