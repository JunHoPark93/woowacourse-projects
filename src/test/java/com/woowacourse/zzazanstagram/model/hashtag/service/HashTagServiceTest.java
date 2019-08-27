package com.woowacourse.zzazanstagram.model.hashtag.service;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.article.domain.vo.Contents;
import com.woowacourse.zzazanstagram.model.article.domain.vo.Image;
import com.woowacourse.zzazanstagram.model.hashtag.domain.HashTag;
import com.woowacourse.zzazanstagram.model.hashtag.domain.TagKeyword;
import com.woowacourse.zzazanstagram.model.hashtag.repository.HashTagRepository;
import com.woowacourse.zzazanstagram.model.hashtag.repository.TagKeywordRepository;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class HashTagServiceTest {
    private Article article;
    private String keyword;

    @InjectMocks
    private HashTagService hashTagService;

    @Mock
    private HashTagRepository hashTagRepository;

    @Mock
    private TagKeywordRepository tagKeywordRepository;


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
    void 해시태그를_제대로_검색하는지_테스트() {
        // given
        TagKeyword tagKeyword = new TagKeyword(keyword);
        List<HashTag> hashTags = Arrays.asList(new HashTag(article, tagKeyword));

        given(tagKeywordRepository.findByTagKeyword(keyword)).willReturn(Optional.of(tagKeyword));
        given(hashTagRepository.findAllByTagKeywordOrderByArticleCreatedDateDesc(tagKeyword)).willReturn(hashTags);

        // then
        assertThat(hashTagService.findAllByTagKeyword(keyword)).isEqualTo(hashTags);
    }

    @Test
    void 존재하지_않는_해시태그를_검색하는_경우_테스트() {
        // given
        given(tagKeywordRepository.findByTagKeyword(keyword)).willReturn(Optional.empty());

        // then
        assertThrows(HashTagException.class, () -> hashTagService.findAllByTagKeyword(keyword));
    }
}
