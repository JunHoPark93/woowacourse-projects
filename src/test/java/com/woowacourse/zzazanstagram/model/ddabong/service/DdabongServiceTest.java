package com.woowacourse.zzazanstagram.model.ddabong.service;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.article.service.ArticleService;
import com.woowacourse.zzazanstagram.model.ddabong.domain.Ddabong;
import com.woowacourse.zzazanstagram.model.ddabong.dto.DdabongToggleResponse;
import com.woowacourse.zzazanstagram.model.ddabong.repository.DdabongRepository;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class DdabongServiceTest {
    private Member author;
    private Ddabong ddabong;

    @InjectMocks
    private DdabongService ddabongService;

    @Mock
    private DdabongRepository ddabongRepository;

    @Mock
    private ArticleService articleService;

    @Mock
    private MemberService memberService;

    @Mock
    private Article article;

    @BeforeEach
    void setUp() {
        author = Member.MemberBuilder.aMember()
                .email(EMAIL)
                .name(NAME)
                .nickName(NICKNAME)
                .password(PASSWORD)
                .profile(IMAGE_URL)
                .build();
        ddabong = new Ddabong(article, author);

    }

    @Test
    void 좋아요_처음_누를때_save_테스트() {
        // given
        given(articleService.findById(1L)).willReturn(article);
        given(memberService.findByEmail(EMAIL)).willReturn(author);
        given(ddabongRepository.findByArticleAndMember(article, author)).willReturn(Optional.empty());
        given(article.countClickedDdabong()).willReturn(1L);

        DdabongToggleResponse ddabongToggleResponse = new DdabongToggleResponse(1, true);

        // when
        DdabongToggleResponse result = ddabongService.findDdabongToggleResponseBy(1L, EMAIL);

        // then
        verify(ddabongRepository, times(1)).save(ddabong);
        assertThat(result).isEqualTo(ddabongToggleResponse);
    }

    @Test
    void 좋아요_취소할때_clicked_변화() {
        // given
        given(articleService.findById(1L)).willReturn(article);
        given(memberService.findByEmail(EMAIL)).willReturn(author);
        given(ddabongRepository.findByArticleAndMember(article, author)).willReturn(Optional.of(ddabong));
        given(article.countClickedDdabong()).willReturn(1L);

        DdabongToggleResponse ddabongToggleResponse = new DdabongToggleResponse(1, false);

        // when
        DdabongToggleResponse result = ddabongService.findDdabongToggleResponseBy(1L, EMAIL);

        // then
        assertThat(result).isEqualTo(ddabongToggleResponse);
    }
}