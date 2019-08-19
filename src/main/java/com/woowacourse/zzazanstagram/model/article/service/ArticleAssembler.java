package com.woowacourse.zzazanstagram.model.article.service;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.article.domain.vo.Contents;
import com.woowacourse.zzazanstagram.model.article.domain.vo.Image;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleRequest;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleResponse;
import com.woowacourse.zzazanstagram.model.member.domain.Member;

import java.time.LocalDateTime;

public class ArticleAssembler {
    public static Article toEntity(ArticleRequest dto, Member author) {
        Image image = Image.of(dto.getImage());
        Contents contents = Contents.of(dto.getContents());

        return new Article(image, contents, author);
    }

    public static ArticleResponse toDto(Article article) {
        Long id = article.getId();
        LocalDateTime createdDate = article.getCreatedDate();
        LocalDateTime lastModifiedDate = article.getLastModifiedDate();

        return ArticleResponse.ArticleResponseBuilder.anArticleResponse()
                .id(id)
                .image(article.getImageValue())
                .contents(article.getContentsValue())
                .nickName(article.getAuthor().getNickNameValue())
                .profileImage(article.getAuthor().getProfileImageValue())
                .createdDate(createdDate)
                .lastModifiedDate(lastModifiedDate)
                .build();
    }
}
