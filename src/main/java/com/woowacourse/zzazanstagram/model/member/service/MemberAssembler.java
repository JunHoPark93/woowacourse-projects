package com.woowacourse.zzazanstagram.model.member.service;

import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.dto.MemberMyPageResponse;
import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;
import com.woowacourse.zzazanstagram.model.member.dto.MemberSignUpRequest;

public class MemberAssembler {
    public static MemberResponse toDto(Member member) {
        return new MemberResponse(member.getId(), member.getNickNameValue(), member.getNameValue(), member.getEmailValue(), member.getProfileImageValue());
    }

    public static Member toEntity(MemberSignUpRequest memberSignupRequest) {
        return Member.MemberBuilder.aMember().email(memberSignupRequest.getEmail())
                .name(memberSignupRequest.getName())
                .nickName(memberSignupRequest.getNickName())
                .password(memberSignupRequest.getPassword())
                .profile(memberSignupRequest.getProfile())
                .build();
    }

    public static MemberMyPageResponse toMyPageResponse(Member member, long articleNumber, long followerNumber, long followeeNumber) {
        return MemberMyPageResponse.builder.aMemberMyPageResponse()
                .id(member.getId())
                .profileImage(member.getProfileImageValue())
                .nickName(member.getNickNameValue())
                .name(member.getNameValue())
                .articleNumber(articleNumber)
                .followeeNumber(followeeNumber)
                .followerNumber(followerNumber)
                .build();

    }
}
