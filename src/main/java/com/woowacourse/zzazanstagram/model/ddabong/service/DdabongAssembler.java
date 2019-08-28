package com.woowacourse.zzazanstagram.model.ddabong.service;

import com.woowacourse.zzazanstagram.model.ddabong.domain.Ddabong;
import com.woowacourse.zzazanstagram.model.ddabong.dto.DdabongMemberResponse;
import com.woowacourse.zzazanstagram.model.ddabong.dto.DdabongToggleResponse;
import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;
import com.woowacourse.zzazanstagram.model.member.service.MemberAssembler;

import java.util.List;
import java.util.stream.Collectors;

public class DdabongAssembler {
    public static DdabongToggleResponse toDto(long count, boolean isClicked) {
        return new DdabongToggleResponse(count, isClicked);
    }

    public static DdabongMemberResponse toDto(List<Ddabong> ddabongs) {
        List<MemberResponse> members = ddabongs.stream()
                .map(Ddabong::getMember)
                .map(MemberAssembler::toDto)
                .collect(Collectors.toList());
        return new DdabongMemberResponse(members);
    }
}
