package com.woowacourse.zzazanstagram.model.member.service;

import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.domain.vo.Email;
import com.woowacourse.zzazanstagram.model.member.domain.vo.NickName;
import com.woowacourse.zzazanstagram.model.member.dto.MemberSignUpRequest;
import com.woowacourse.zzazanstagram.model.member.exception.MemberNotFoundException;
import com.woowacourse.zzazanstagram.model.member.exception.MemberSaveException;
import com.woowacourse.zzazanstagram.model.member.repository.MemberRepository;
import mockit.Deencapsulation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class MemberServiceTest {
    private NickName nickName;
    private Email email;
    private Member member;

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        nickName = NickName.of(NICKNAME);
        email = Email.of(EMAIL);
        member = Member.MemberBuilder.aMember()
                .email(EMAIL)
                .name(NAME)
                .nickName(NICKNAME)
                .password(PASSWORD)
                .profile(IMAGE_URL)
                .build();
    }

    @Test
    public void 존재하는_회원을_검색하는_경우_테스트() {
        // given
        given(memberRepository.findByEmail(email)).willReturn(Optional.of(member));

        // then
        assertThat(memberService.findByEmail(EMAIL)).isEqualTo(member);
    }

    @Test
    public void 존재하지_않는_회원을_검색하는_경우_테스트() {
        // given
        given(memberRepository.findByEmail(email)).willReturn(Optional.empty());

        // then
        assertThrows(MemberNotFoundException.class, () -> memberService.findByEmail(EMAIL));
    }

    @Test
    public void 회원가입_성공_테스트() {
        // given
        MemberSignUpRequest request = getMemberSignUpRequest();
        Member member = Deencapsulation.invoke(MemberAssembler.class, "toEntity", request);

        given(memberRepository.existsByNickNameOrEmail(nickName, email)).willReturn(false);
        given(memberRepository.save(member)).willReturn(member);

        // when
        memberService.save(request);

        // then
        verify(memberRepository, times(1)).save(member);
    }

    @Test
    public void 존재하는_이메일로_가입하는_경우_테스트() {
        // given
        MemberSignUpRequest request = getMemberSignUpRequest();

        given(memberRepository.existsByNickNameOrEmail(nickName, email)).willReturn(true);

        // then
        assertThrows(MemberSaveException.class, () -> memberService.save(request));
    }

    private MemberSignUpRequest getMemberSignUpRequest() {
        MemberSignUpRequest request = new MemberSignUpRequest();
        request.setEmail(EMAIL);
        request.setName(NAME);
        request.setNickName(NICKNAME);
        request.setPassword(PASSWORD);
        request.setProfile(IMAGE_URL);

        return request;
    }
} 