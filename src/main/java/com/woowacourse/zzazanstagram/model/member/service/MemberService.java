package com.woowacourse.zzazanstagram.model.member.service;

import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.domain.vo.Email;
import com.woowacourse.zzazanstagram.model.member.domain.vo.NickName;
import com.woowacourse.zzazanstagram.model.member.dto.MemberSignUpRequest;
import com.woowacourse.zzazanstagram.model.member.exception.MemberNotFoundException;
import com.woowacourse.zzazanstagram.model.member.exception.MemberSaveException;
import com.woowacourse.zzazanstagram.model.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(Email.of(email))
                .orElseThrow(() -> new MemberNotFoundException("잘못된 접근입니다."));
    }

    public void save(MemberSignUpRequest memberSignupRequest) {
        if (checkEnrolledEmailOrNickName(memberSignupRequest)) {
            throw new MemberSaveException("이미 존재하는 이메일 또는 닉네임 입니다.");
        }
        Member member = MemberAssembler.toEntity(memberSignupRequest);
        memberRepository.save(member);
    }

    private boolean checkEnrolledEmailOrNickName(MemberSignUpRequest memberSignupRequest) {
        NickName nickName = NickName.of(memberSignupRequest.getNickName());
        Email email = Email.of(memberSignupRequest.getEmail());
        return memberRepository.existsByNickNameOrEmail(nickName, email);
    }
}
