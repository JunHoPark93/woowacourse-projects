package com.woowacourse.zzazanstagram.model.hashtag.service;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.article.domain.vo.Contents;
import com.woowacourse.zzazanstagram.model.article.domain.vo.Image;
import com.woowacourse.zzazanstagram.model.hashtag.domain.ArticleHashtag;
import com.woowacourse.zzazanstagram.model.hashtag.domain.Hashtag;
import com.woowacourse.zzazanstagram.model.hashtag.exception.HashTagException;
import com.woowacourse.zzazanstagram.model.hashtag.repository.ArticleHashtagRepository;
import com.woowacourse.zzazanstagram.model.hashtag.repository.HashtagRepository;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class ArticleHashtagServiceTest {
    private Article article;
    private String keyword;

    @InjectMocks
    private HashtagService hashtagService;

    @Mock
    private ArticleHashtagRepository articleHashtagRepository;

    @Mock
    private HashtagRepository hashtagRepository;


    @BeforeEach
    void setUp() {
        this.article = new Article(Image.of(IMAGE_URL), Contents.of(CONTENTS),
                Member.MemberBuilder
                        .aMember()
                        .email(EMAIL)
                        .name(NAME)
                        .nickName(NICKNAME)
                        .password(PASSWORD)
                        .profile(IMAGE_URL)
                        .build());
        this.keyword = "해시태그";
    }

    @Test
    void save() {
        // given
        Hashtag hashTag = new Hashtag(keyword);
        List<ArticleHashtag> articleHashtags = Arrays.asList(new ArticleHashtag(article, hashTag));

        given(hashtagRepository.findByKeyword(keyword)).willReturn(Optional.of(hashTag));
        given(articleHashtagRepository.findAllByHashtagOrderByArticleCreatedDateDesc(hashTag)).willReturn(articleHashtags);

        // then
        assertThat(hashtagService.findAllByHashtag(keyword)).isEqualTo(articleHashtags);
    }

    @Test
    void 해시태그를_제대로_검색하는지_테스트() {
        // given
        Hashtag hashTag = new Hashtag(keyword);
        List<ArticleHashtag> articleHashtags = Arrays.asList(new ArticleHashtag(article, hashTag));

        given(hashtagRepository.findByKeyword(keyword)).willReturn(Optional.of(hashTag));
        given(articleHashtagRepository.findAllByHashtagOrderByArticleCreatedDateDesc(hashTag)).willReturn(articleHashtags);

        // then
        assertThat(hashtagService.findAllByHashtag(keyword)).isEqualTo(articleHashtags);
    }

    @Test
    void 존재하지_않는_해시태그를_검색하는_경우_테스트() {
        // given
        given(hashtagRepository.findByKeyword(keyword)).willReturn(Optional.empty());

        // then
        assertThrows(HashTagException.class, () -> hashtagService.findAllByHashtag(keyword));
    }
}
