package com.woowacourse.zzazanstagram.model.hashtag.domain;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.article.domain.vo.Contents;
import com.woowacourse.zzazanstagram.model.article.domain.vo.Image;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.*;
import static org.assertj.core.api.Assertions.assertThat;

class ArticleHashtagTest {
    private Article article;
    private Hashtag hashtag;

    private ArticleHashtag articleHashtag;

    @BeforeEach
    void setUp() {
        article = new Article(Image.of(IMAGE_URL), Contents.of(CONTENTS), Member.MemberBuilder.aMember().build());
        hashtag = new Hashtag(KEYWORD_1);

        articleHashtag = new ArticleHashtag(article, hashtag);

        ReflectionTestUtils.setField(article, "id", 1L);
        ReflectionTestUtils.setField(hashtag, "id", 1L);

        ReflectionTestUtils.setField(articleHashtag, "id", 1L);
    }

    @Test
    void create() {
        ArticleHashtag articleHashtag = new ArticleHashtag();
        ReflectionTestUtils.setField(articleHashtag, "id", 1L);

        assertThat(articleHashtag).isEqualTo(this.articleHashtag);
    }

    @Test
    void id값이_다른_객체_비교_테스트() {
        ArticleHashtag anotherArticleHashtag = new ArticleHashtag();
        ReflectionTestUtils.setField(anotherArticleHashtag, "id", 2L);

        assertThat(anotherArticleHashtag).isNotEqualTo(this.articleHashtag);
    }

    @Test
    void getArticle() {
        assertThat(articleHashtag.getArticle()).isEqualTo(article);
    }

    @Test
    void getHashtag() {
        assertThat(articleHashtag.getHashtag()).isEqualTo(hashtag);
    }
}