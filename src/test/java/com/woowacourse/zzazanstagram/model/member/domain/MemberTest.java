package com.woowacourse.zzazanstagram.model.member.domain;

import com.woowacourse.zzazanstagram.model.member.domain.vo.Password;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.test.util.ReflectionTestUtils;

import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.EMAIL;
import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberTest {
    private Member member;

    @BeforeEach
    void setUp() {
        member = Member.MemberBuilder.aMember().build();
        ReflectionTestUtils.setField(member, "id", 1L);

    }

    @Test
    void create() {
        Member member = new Member();
        ReflectionTestUtils.setField(member, "id", 1L);

        assertThat(member).isEqualTo(member);
    }

    @Test
    void id값이_다른_객체_비교_테스트() {
        Member member = new Member();
        ReflectionTestUtils.setField(member, "id", 2L);

        assertThat(member).isNotEqualTo(this.member);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "11111111111"})
    void 닉네임_비정상_체크(String nickName) {
        assertThrows(IllegalArgumentException.class, () ->
                Member.MemberBuilder.aMember().nickName(nickName));
    }

    @ParameterizedTest
    @ValueSource(strings = {"name", "nick"})
    void 닉네임_정상_체크(String nickName) {
        assertThatCode(() ->
                Member.MemberBuilder.aMember().nickName(nickName))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "11111111111"})
    void 이름_비정상_체크(String name) {
        assertThrows(IllegalArgumentException.class, () ->
                Member.MemberBuilder.aMember().name(name));
    }

    @ParameterizedTest
    @ValueSource(strings = {"name", "nicky"})
    void 이름_정상_체크(String name) {
        assertThatCode(() ->
                Member.MemberBuilder.aMember().name(name))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"dfsdfsdf", "email@"})
    void 이메일_비정상_체크(String email) {
        assertThrows(IllegalArgumentException.class, () ->
                Member.MemberBuilder.aMember().email(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"test@gmail.com", "dfsdfsdf@naver.com"})
    void 이메일_정상_체크(String email) {
        assertThatCode(() ->
                Member.MemberBuilder.aMember().email(email))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"dfdf", "dfsf/!", "DDff"})
    void 비밀번호_비정상_체크(String password) {
        assertThrows(IllegalArgumentException.class, () ->
                Member.MemberBuilder.aMember().password(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Password!1", "dffDzzzF2!"})
    void 비밀번호_정상_체크(String password) {
        assertThatCode(() ->
                Member.MemberBuilder.aMember().password(password))
                .doesNotThrowAnyException();
    }

    @Test
    void 원문과_암호화된_비밀번호_비교() {
        String input = "Password!1";
        Password password = Password.of(input);
        assertThat(password.isMatch(input)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"https://image.shutterstock.com/image-photo/white-transparent-leaf-on-mirror-600w-1029171697.jpg",
            "https://image.shutterstock.com/image-photo/bright-spring-view-cameo-island-600w-1048185397.jpg"})
    void 프로필_url_정상_체크(String profile) {
        assertThatCode(() ->
                Member.MemberBuilder.aMember().profile(profile))
                .doesNotThrowAnyException();
    }


    @Test
    void 비밀번호가_일치하는지_테스트() {
        Member member = Member.MemberBuilder.aMember().password(PASSWORD).build();

        assertThat(member.isMatchPassword(PASSWORD)).isTrue();
    }

    @Test
    void 비밀번호가_일치하지_않는_경우_테스트() {
        Member member = Member.MemberBuilder.aMember().password(PASSWORD).build();

        assertThat(member.isMatchPassword("Wrong" + PASSWORD)).isFalse();
    }

    @Test
    void 같은_이메일을_가진_사용자인지_테스트() {
        Member member = Member.MemberBuilder.aMember().email(EMAIL).build();
        Member sameMember = Member.MemberBuilder.aMember().email(EMAIL).build();

        assertThat(member.isSame(sameMember)).isTrue();
    }

    @Test
    void 같은_이메일을_가진_사용자가_아닌_경우_테스트() {
        Member member = Member.MemberBuilder.aMember().email(EMAIL).build();
        Member differentMember = Member.MemberBuilder.aMember().email("different" + EMAIL).build();

        assertThat(member.isSame(differentMember)).isFalse();
    }
}