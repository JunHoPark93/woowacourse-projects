package com.woowacourse.zzazanstagram.model.search.service;

import com.woowacourse.zzazanstagram.model.hashtag.domain.Hashtag;
import com.woowacourse.zzazanstagram.model.hashtag.dto.HashtagResponse;
import com.woowacourse.zzazanstagram.model.hashtag.service.ArticleHashtagService;
import com.woowacourse.zzazanstagram.model.hashtag.service.HashtagAssembler;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;
import com.woowacourse.zzazanstagram.model.member.service.MemberAssembler;
import com.woowacourse.zzazanstagram.model.member.service.MemberService;
import com.woowacourse.zzazanstagram.model.search.dto.SearchResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;

import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class SearchServiceTest {
    private Member member;
    private Hashtag hashtag;

    @InjectMocks
    private SearchService searchService;

    @Mock
    private MemberService memberService;

    @Mock
    private ArticleHashtagService articleHashtagService;

    @BeforeEach
    void setUp() {
        member = Member.MemberBuilder.aMember()
                .email(EMAIL)
                .name(NAME)
                .nickName(NICKNAME)
                .password(PASSWORD)
                .profile(IMAGE_URL)
                .build();
        ReflectionTestUtils.setField(member, "id", 1L);

        hashtag = new Hashtag("keyword");
    }

    @Test
    void searchByKeyword() {
        String keyword = "key";
        int maxSizeOfNickName = 1;
        int maxSizeOfHashtag = 1;

        MemberResponse memberResponse = MemberAssembler.toDto(member);
        List<MemberResponse> memberResponses = Collections.singletonList(memberResponse);
        given(memberService.findMemberResponsesByNickName(keyword, maxSizeOfNickName)).willReturn(memberResponses);

        HashtagResponse hashtagResponse = HashtagAssembler.toDto(hashtag);
        List<HashtagResponse> hashtagResponses = Collections.singletonList(hashtagResponse);
        given(articleHashtagService.findHashtagResponsesByKeyword(keyword, maxSizeOfHashtag)).willReturn(hashtagResponses);

        SearchResponse searchResponse = new SearchResponse(memberResponses, hashtagResponses);
        assertThat(searchService.searchByKeyword(keyword, maxSizeOfNickName, maxSizeOfHashtag)).isEqualTo(searchResponse);
    }
}