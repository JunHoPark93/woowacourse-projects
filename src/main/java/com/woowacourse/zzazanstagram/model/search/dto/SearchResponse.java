package com.woowacourse.zzazanstagram.model.search.dto;

import com.woowacourse.zzazanstagram.model.hashtag.dto.HashtagResponse;
import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;

import java.util.List;
import java.util.Objects;

public class SearchResponse {
    private List<MemberResponse> memberResponses;
    private List<HashtagResponse> hashtagResponses;

    private SearchResponse() {
    }

    public SearchResponse(List<MemberResponse> memberResponses, List<HashtagResponse> hashtagResponses) {
        this.memberResponses = memberResponses;
        this.hashtagResponses = hashtagResponses;
    }

    public List<MemberResponse> getMemberResponses() {
        return memberResponses;
    }

    public List<HashtagResponse> getHashtagResponses() {
        return hashtagResponses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final SearchResponse that = (SearchResponse) o;
        return Objects.equals(memberResponses, that.memberResponses) &&
                Objects.equals(hashtagResponses, that.hashtagResponses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberResponses, hashtagResponses);
    }
}
