package com.woowacourse.zzazanstagram.model.ddabong.domain;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.article.domain.vo.Contents;
import com.woowacourse.zzazanstagram.model.article.domain.vo.Image;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.CONTENTS;
import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.IMAGE_URL;
import static org.assertj.core.api.Assertions.assertThat;

class DdabongTest {
    private Article article;
    private Member author;
    private Ddabong ddabong;

    @BeforeEach
    void setUp() {
        author = Member.MemberBuilder.aMember()
                .nickName("nickName")
                .name("name")
                .email("test@test.com")
                .password("password1!")
                .profile("https://image.shutterstock.com/image-photo/600w-1029171697.jpg")
                .build();
        article = new Article(Image.of(IMAGE_URL), Contents.of(CONTENTS), author);
        ddabong = new Ddabong(article, author);
    }

    @Test
    void 좋아요_생성_테스트() {
        assertThat(ddabong).isEqualTo(new Ddabong(article, author));
    }
}