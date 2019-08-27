package com.woowacourse.zzazanstagram.model.ddabong.dto;

import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;

import java.util.List;

public class DdabongMemberResponse {
    private List<MemberResponse> memberResponses;

    public DdabongMemberResponse(List<MemberResponse> memberResponses) {
        this.memberResponses = memberResponses;
    }

    public List<MemberResponse> getMemberResponses() {
        return memberResponses;
    }
}
