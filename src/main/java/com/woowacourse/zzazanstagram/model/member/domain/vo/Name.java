package com.woowacourse.zzazanstagram.model.member.domain.vo;

import com.woowacourse.zzazanstagram.model.member.exception.MemberNameFormatException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Name {
    private static final String NAME_REGEX = "[A-Za-zㄱ-ㅎㅏ-ㅣ가-힣]{2,10}";

    @Column(name = "name")
    private String name;

    private Name() {
    }

    private Name(final String name) {
        this.name = validate(name);
    }

    public static Name of(final String name) {
        return new Name(name);
    }

    private String validate(final String name) {
        if (isMismatch(name)) {
            throw new MemberNameFormatException("이름은 2자 이상 10자 이하입니다.");
        }
        return name;
    }

    private boolean isMismatch(String name) {
        return !name.matches(NAME_REGEX);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
