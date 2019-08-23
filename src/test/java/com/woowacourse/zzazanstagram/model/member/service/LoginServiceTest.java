package com.woowacourse.zzazanstagram.model.member.service;

import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.domain.vo.Email;
import com.woowacourse.zzazanstagram.model.member.dto.MemberLoginRequest;
import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;
import com.woowacourse.zzazanstagram.model.member.exception.MemberLoginFailException;
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

@ExtendWith(SpringExtension.class)
class LoginServiceTest {
    private Email email;
    private Member member;

    @InjectMocks
    private LoginService loginService;

    @Mock
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
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
    public void 정상적인_로그인_테스트() {
        // given
        MemberLoginRequest request = new MemberLoginRequest(EMAIL, PASSWORD);

        MemberResponse memberResponse = Deencapsulation.invoke(MemberAssembler.class, "assemble", member);
        given(memberRepository.findByEmail(email)).willReturn(Optional.of(member));

        // then
        assertThat(loginService.find(request)).isEqualTo(memberResponse);
    }

    @Test
    public void 존재하지_않는_정보로_로그인하는_경우_테스트() {
        // given
        MemberLoginRequest request = new MemberLoginRequest(EMAIL, PASSWORD);

        given(memberRepository.findByEmail(email)).willReturn(Optional.empty());

        // then
        assertThrows(MemberLoginFailException.class, () -> loginService.find(request));
    }

    @Test
    public void 비밀번호가_일치하지_않는_경우_테스트() {
        // given
        MemberLoginRequest request = new MemberLoginRequest(EMAIL, "WRONG" + PASSWORD);

        given(memberRepository.findByEmail(email)).willReturn(Optional.of(member));

        // then
        assertThrows(MemberLoginFailException.class, () -> loginService.find(request));
    }
}