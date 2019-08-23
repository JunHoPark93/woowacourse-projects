package com.woowacourse.zzazanstagram.model.member.dto;

import java.util.Objects;

public class MemberResponse {
    private Long id;
    private String nickName;
    private String name;
    private String email;
    private String profileImage;

    private MemberResponse() {
    }

    public MemberResponse(Long id, String nickName, String name, String email, String profileImage) {
        this.id = id;
        this.nickName = nickName;
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberResponse that = (MemberResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nickName, that.nickName) &&
                Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(profileImage, that.profileImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickName, name, email, profileImage);
    }
}
