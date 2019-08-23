package com.woowacourse.zzazanstagram.model.member.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class ProfileImage {

    @Column(name = "profile_image_url")
    private String url;

    private ProfileImage() {
    }

    private ProfileImage(final String url) {
        this.url = url;
    }

    public static ProfileImage of(final String url) {
        return new ProfileImage(url);
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileImage that = (ProfileImage) o;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
