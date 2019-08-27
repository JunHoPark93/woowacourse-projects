package com.woowacourse.zzazanstagram.model.hashtag.service;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.article.domain.vo.Contents;
import com.woowacourse.zzazanstagram.model.article.domain.vo.Image;
import com.woowacourse.zzazanstagram.model.hashtag.domain.HashTag;
import com.woowacourse.zzazanstagram.model.hashtag.repository.HashTagRepository;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class HashTagServiceTest {
    private Article article;

    @InjectMocks
    private HashTagService hashTagService;

    @Mock
    private HashTagRepository hashTagRepository;

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
    }

    @Test
    void save() {
        // given
        List<HashTag> hashTags = article.extractTagKeywords().stream()
                .map(t -> new HashTag(article, t))
                .collect(Collectors.toList());

        // when
        hashTagService.save(article);

        // then
        verify(hashTagRepository, times(1)).saveAll(hashTags);
    }
}
