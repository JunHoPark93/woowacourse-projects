package com.woowacourse.zzazanstagram.model.member.service;

import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.domain.vo.Email;
import com.woowacourse.zzazanstagram.model.member.domain.vo.NickName;
import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;
import com.woowacourse.zzazanstagram.model.member.dto.MemberSignUpRequest;
import com.woowacourse.zzazanstagram.model.member.exception.MemberNotFoundException;
import com.woowacourse.zzazanstagram.model.member.exception.MemberSaveException;
import com.woowacourse.zzazanstagram.model.member.repository.MemberRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(Email.of(email))
                .orElseThrow(() -> new MemberNotFoundException("잘못된 접근입니다."));
    }

    public void save(MemberSignUpRequest memberSignupRequest) {
        if (isEnrolledNickNameOrEmail(memberSignupRequest)) {
            throw new MemberSaveException("이미 존재하는 이메일 또는 닉네임 입니다.");
        }
        Member member = MemberAssembler.toEntity(memberSignupRequest);
        memberRepository.save(member);
    }

    private boolean isEnrolledNickNameOrEmail(MemberSignUpRequest memberSignupRequest) {
        Email email = Email.of(memberSignupRequest.getEmail());
        NickName nickName = NickName.of(memberSignupRequest.getNickName());

        return memberRepository.existsByNickNameOrEmail(nickName, email);
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException("잘못된 접근입니다."));
    }

    public MemberResponse findMemberResponseByNickName(String nickName) {
        Member member = findByNickName(nickName);
        return MemberAssembler.toDto(member);
    }

    public Member findByNickName(String nickName) {
        return memberRepository.findByNickName(NickName.of(nickName)).orElseThrow(() -> new MemberNotFoundException("잘못된 접근입니다."));
    }

    public List<Member> findAllByIds(List<Long> ids) {
        return memberRepository.findByIdIn(ids);
    }

    @Transactional(readOnly = true)
    public List<MemberResponse> findMemberResponsesByNickName(String keyword, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Member> members = memberRepository.findByNickNameContaining(keyword, pageRequest);

        return members.stream()
                .map(MemberAssembler::toDto)
                .collect(Collectors.toList());
    }
}
