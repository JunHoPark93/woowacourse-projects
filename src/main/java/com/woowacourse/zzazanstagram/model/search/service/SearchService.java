package com.woowacourse.zzazanstagram.model.search.service;

import com.woowacourse.zzazanstagram.model.hashtag.dto.HashtagResponse;
import com.woowacourse.zzazanstagram.model.hashtag.service.ArticleHashtagService;
import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;
import com.woowacourse.zzazanstagram.model.member.service.MemberService;
import com.woowacourse.zzazanstagram.model.search.dto.SearchResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SearchService {
    private final MemberService memberService;
    private final ArticleHashtagService articleHashtagService;

    public SearchService(MemberService memberService, ArticleHashtagService articleHashtagService) {
        this.memberService = memberService;
        this.articleHashtagService = articleHashtagService;
    }

    @Transactional(readOnly = true)
    public SearchResponse searchByKeyword(String keyword, int maxSizeOfNickName, int maxSizeOfHashtag) {
        List<MemberResponse> memberResponses = memberService.findMemberResponsesByNickName(keyword, maxSizeOfNickName);
        List<HashtagResponse> hashtagResponses = articleHashtagService.findHashtagResponsesByKeyword(keyword, maxSizeOfHashtag);

        return new SearchResponse(memberResponses, hashtagResponses);
    }
}
