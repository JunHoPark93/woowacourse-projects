package com.woowacourse.zzazanstagram.model.member.domain.vo;

import com.woowacourse.zzazanstagram.model.member.exception.MemberNickNameFormatException;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class NickName {
    private static final String NICK_NAME_REGEX = "[0-9A-Za-zㄱ-ㅎㅏ-ㅣ가-힣]{2,10}";

    @Column(name = "nick_name", unique = true)
    private String nickName;

    private NickName(final String name) {
        this.nickName = validate(name);
    }

    private NickName() {
    }

    public static NickName of(final String name) {
        return new NickName(name);
    }

    private String validate(final String name) {
        if (isMismatch(name)) {
            throw new MemberNickNameFormatException("이름은 2자 이상 10자 이하입니다.");
        }
        return name;
    }

    private boolean isMismatch(String name) {
        return !name.matches(NICK_NAME_REGEX);
    }

    public String getNickName() {
        return nickName;
    }
}
