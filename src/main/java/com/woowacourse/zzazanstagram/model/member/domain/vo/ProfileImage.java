package com.woowacourse.zzazanstagram.model.member.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProfileImage {

    @Column(name = "profile_image_url")
    private String url;

    private ProfileImage(final String url) {
        this.url = url;
    }

    private ProfileImage() {
    }

    public static ProfileImage of(final String url) {
        return new ProfileImage(url);
    }

    public String getUrl() {
        return url;
    }
}
