package com.woowacourse.zzazanstagram.model.member.domain;

import com.woowacourse.zzazanstagram.model.common.BaseEntity;
import com.woowacourse.zzazanstagram.model.member.domain.vo.*;

import javax.persistence.Entity;

@Entity
public class Member extends BaseEntity {
    private NickName nickName;
    private Name name;
    private Email email;
    private Password password;
    private ProfileImage profileImage;

    protected Member() {
    }

    public boolean isMatchPassword(String password) {
        return this.password.isMatch(password);
    }

    public boolean isSame(Member member) {
        return this.email.equals(member.getEmail());
    }

    public NickName getNickName() {
        return nickName;
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public String getNameValue() {
        return name.getName();
    }

    public String getEmailValue() {
        return email.getEmail();
    }

    public String getNickNameValue() {
        return nickName.getNickName();
    }

    public String getProfileImageValue() {
        return profileImage.getUrl();
    }

    public static final class MemberBuilder {
        private NickName nickName;
        private Name name;
        private Email email;
        private Password password;
        private ProfileImage profileImage;

        private MemberBuilder() {
        }

        public static MemberBuilder aMember() {
            return new MemberBuilder();
        }

        public MemberBuilder nickName(String nickName) {
            this.nickName = NickName.of(nickName);
            return this;
        }

        public MemberBuilder name(String name) {
            this.name = Name.of(name);
            return this;
        }

        public MemberBuilder email(String email) {
            this.email = Email.of(email);
            return this;
        }

        public MemberBuilder password(String password) {
            this.password = Password.of(password);
            return this;
        }

        public MemberBuilder profile(String profile) {
            this.profileImage = ProfileImage.of(profile);
            return this;
        }

        public Member build() {
            Member member = new Member();
            member.name = this.name;
            member.nickName = this.nickName;
            member.email = this.email;
            member.password = this.password;
            member.profileImage = this.profileImage;
            return member;
        }
    }
}
