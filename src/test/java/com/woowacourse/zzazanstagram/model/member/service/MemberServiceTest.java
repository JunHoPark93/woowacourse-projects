package com.woowacourse.zzazanstagram.model.member.service;

import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.domain.vo.Email;
import com.woowacourse.zzazanstagram.model.member.domain.vo.NickName;
import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
        ReflectionTestUtils.setField(member, "id", 1L);
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

    @Test
    void id로_사용자_검색하는_경우_테스트() {
        // given
        given(memberRepository.findById(1L)).willReturn(Optional.of(member));

        // when
        Member result = memberService.findById(1L);

        // then
        assertThat(result).isEqualTo(this.member);
    }

    @Test
    void 존재하지_않는_id로_사용자를_검색하는_경우_예외처리() {
        // given
        given(memberRepository.findById(1L)).willReturn(Optional.empty());

        // then
        assertThrows(MemberNotFoundException.class, () -> memberService.findById(1L));
    }

    @Test
    void 닉네임으로_사용자를_검색하는_경우_테스트() {
        // given
        given(memberRepository.findByNickName(nickName)).willReturn(Optional.of(member));
        MemberResponse memberResponse = Deencapsulation.invoke(MemberAssembler.class, "toDto", member);

        // when
        MemberResponse result = memberService.findMemberResponseByNickName(NICKNAME);

        // then
        assertThat(result).isEqualTo(memberResponse);
    }

    @Test
    void 존재하지_않는_닉네임으로_사용자를_검색하는_경우_예외처리() {
        // given
        given(memberRepository.findByNickName(nickName)).willReturn(Optional.empty());

        // then
        assertThrows(MemberNotFoundException.class, () -> memberService.findMemberResponseByNickName(NICKNAME));
    }

    @Test
    void id리스트로_모든_사용자_조회하는_테스트() {
        // given
        List<Long> ids = Arrays.asList(1L, 2L, 3L);

        // when
        memberService.findAllByIds(ids);

        // then
        verify(memberRepository, times(1)).findByIdIn(ids);
    }

    @Test
    void findMemberResponsesByNickName() {
        int defaultPageNum = 0;
        int maxSizeOfNickName = 1;
        PageRequest pageRequest = PageRequest.of(defaultPageNum, maxSizeOfNickName);
        given(memberRepository.findByNickNameContaining(NICKNAME, pageRequest)).willReturn(Collections.singletonList(member));

        MemberResponse memberResponse = MemberAssembler.toDto(member);
        assertThat(memberService.findMemberResponsesByNickName(NICKNAME, defaultPageNum, maxSizeOfNickName))
                .isEqualTo(Collections.singletonList(memberResponse));
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