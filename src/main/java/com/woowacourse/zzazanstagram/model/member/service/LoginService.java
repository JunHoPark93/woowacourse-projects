package com.woowacourse.zzazanstagram.model.member.service;

import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.domain.vo.Email;
import com.woowacourse.zzazanstagram.model.member.dto.MemberLoginRequest;
import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;
import com.woowacourse.zzazanstagram.model.member.exception.MemberLoginFailException;
import com.woowacourse.zzazanstagram.model.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    private static final String ERROR_ILLEGAL_LOGIN_MESSAGE = "로그인 정보가 올바르지 않습니다.";
    private final MemberRepository memberRepository;

    public LoginService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponse find(MemberLoginRequest request) {
        Member member = checkEnrolledMember(request);
        return MemberAssembler.assemble(member);
    }

    private Member checkEnrolledMember(MemberLoginRequest request) {
        return findByEmail(request.getEmail())
                .filter(m -> m.isMatchPassword(request.getPassword()))
                .orElseThrow(() -> new MemberLoginFailException(ERROR_ILLEGAL_LOGIN_MESSAGE));
    }

    private Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(Email.of(email));
    }
}
