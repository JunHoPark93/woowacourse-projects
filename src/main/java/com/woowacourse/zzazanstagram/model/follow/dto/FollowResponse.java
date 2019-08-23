package com.woowacourse.zzazanstagram.model.follow.dto;

import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;

public class FollowResponse {
    private MemberResponse memberResponse;

    public FollowResponse(MemberResponse memberResponse) {
        this.memberResponse = memberResponse;
    }

    public MemberResponse getMemberResponse() {
        return memberResponse;
    }

    public void setMemberResponse(MemberResponse memberResponse) {
        this.memberResponse = memberResponse;
    }
}
