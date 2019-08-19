package com.woowacourse.zzazanstagram.model.member.service;

import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;
import com.woowacourse.zzazanstagram.model.member.dto.MemberSignUpRequest;

public class MemberAssembler {
    static MemberResponse assemble(Member member) {
        return new MemberResponse(member.getNickNameValue(), member.getNameValue(), member.getEmailValue(), member.getProfileImageValue());
    }

    static Member toEntity(MemberSignUpRequest memberSignupRequest) {
        return Member.MemberBuilder.aMember().email(memberSignupRequest.getEmail())
                .name(memberSignupRequest.getName())
                .nickName(memberSignupRequest.getNickName())
                .password(memberSignupRequest.getPassword())
                .profile(memberSignupRequest.getProfile())
                .build();
    }
}
