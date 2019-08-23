package com.woowacourse.zzazanstagram.model.member.domain.vo;

import com.woowacourse.zzazanstagram.model.member.exception.MemberEmailFormatException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Email {
    private static final String EMAIL_REGEX = "^[_a-zA-Z0-9-.]+@[.a-zA-Z0-9-]+\\.[a-zA-Z]+$";

    @Column(name = "email", unique = true)
    private String email;

    private Email() {
    }

    private Email(final String email) {
        this.email = validate(email);
    }

    public static Email of(final String email) {
        return new Email(email);
    }

    private String validate(final String email) {
        if (isMismatch(email)) {
            throw new MemberEmailFormatException("잘못된 형식의 이메일입니다.");
        }
        return email;
    }

    private boolean isMismatch(String email) {
        return !email.matches(EMAIL_REGEX);
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
