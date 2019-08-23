package com.woowacourse.zzazanstagram.model.member;

import java.io.Serializable;

public class MemberSession implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String nickName;
    private String profileImage;

    public MemberSession(Long id, String name, String email, String nickName, String profileImage) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.nickName = nickName;
        this.profileImage = profileImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
