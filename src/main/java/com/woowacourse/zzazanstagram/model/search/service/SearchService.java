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
    private static final int DEFAULT_PAGE_NUM = 0;
    private static final int DEFAULT_MAX_SIZE = 5;
    private final MemberService memberService;
    private final ArticleHashtagService articleHashtagService;

    public SearchService(MemberService memberService, ArticleHashtagService articleHashtagService) {
        this.memberService = memberService;
        this.articleHashtagService = articleHashtagService;
    }

    @Transactional(readOnly = true)
    public SearchResponse searchByKeyword(String keyword, int maxSizeOfNickName, int maxSizeOfHashtag) {
        List<MemberResponse> memberResponses
                = memberService.findMemberResponsesByNickName(keyword, DEFAULT_PAGE_NUM, validateMaxSize(maxSizeOfNickName));
        List<HashtagResponse> hashtagResponses
                = articleHashtagService.findHashtagResponsesByKeyword(keyword, DEFAULT_PAGE_NUM, validateMaxSize(maxSizeOfHashtag));

        return new SearchResponse(memberResponses, hashtagResponses);
    }

    private int validateMaxSize(int size) {
        return (size > DEFAULT_MAX_SIZE) ? DEFAULT_MAX_SIZE : size;
    }
}
