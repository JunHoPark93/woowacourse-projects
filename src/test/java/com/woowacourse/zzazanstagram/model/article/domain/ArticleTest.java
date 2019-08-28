package com.woowacourse.zzazanstagram.model.article.domain;

import com.woowacourse.zzazanstagram.model.article.domain.vo.Contents;
import com.woowacourse.zzazanstagram.model.article.domain.vo.Image;
import com.woowacourse.zzazanstagram.model.hashtag.domain.Hashtag;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.CONTENTS;
import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.IMAGE_URL;
import static org.assertj.core.api.Assertions.assertThat;

class ArticleTest {
    private Image image;
    private Contents contents;
    private Article article;
    private Member author;

    @BeforeEach
    void setUp() {
        image = Image.of(IMAGE_URL);
        contents = Contents.of(CONTENTS);
        author = Member.MemberBuilder.aMember()
                .nickName("nickName")
                .name("name")
                .email("test@test.com")
                .password("password1!")
                .profile("https://image.shutterstock.com/image-photo/600w-1029171697.jpg")
                .build();
        article = new Article(image, contents, author);
    }

    @Test
    void Article_객체_생성() {
        assertThat(article).isEqualTo(new Article(image, contents, author));
    }

    @Test
    void extractTagKeywords() {
        List<String> expected = Arrays.asList("아이크", "닉", "뚱이", "제이", "에헴");

        assertThat(article.extractTagKeywords()
                .stream()
                .map(Hashtag::getKeyword)
                .collect(Collectors.toList()))
                .isEqualTo(expected);
    }
}