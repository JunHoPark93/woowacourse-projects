package com.woowacourse.zzazanstagram.model.hashtag.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.KEYWORD_1;
import static org.assertj.core.api.Assertions.assertThat;

class HashtagTest {
    private Hashtag hashtag;

    @BeforeEach
    void setUp() {
        hashtag = new Hashtag(KEYWORD_1);

        ReflectionTestUtils.setField(hashtag, "id", 1L);
    }

    @Test
    void create() {
        Hashtag hashtag = new Hashtag(KEYWORD_1);
        ReflectionTestUtils.setField(hashtag, "id", 1L);

        assertThat(hashtag).isEqualTo(this.hashtag);
    }

    @Test
    void id값이_다른_객체_비교_테스트() {
        Hashtag hashtag = new Hashtag(KEYWORD_1);
        ReflectionTestUtils.setField(hashtag, "id", 2L);

        assertThat(hashtag).isNotEqualTo(this.hashtag);
    }

    @Test
    void getKeyword() {
        assertThat(hashtag.getKeyword()).isEqualTo(KEYWORD_1);
    }
}