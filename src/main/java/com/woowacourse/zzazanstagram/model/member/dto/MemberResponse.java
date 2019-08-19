package com.woowacourse.zzazanstagram.model.member.dto;

public class MemberResponse {
    private String nickName;
    private String name;
    private String email;
    private String profileImage;

    public MemberResponse(String nickName, String name, String email, String profileImage) {
        this.nickName = nickName;
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
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
}
