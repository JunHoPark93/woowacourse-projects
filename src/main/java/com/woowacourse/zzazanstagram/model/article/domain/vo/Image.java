package com.woowacourse.zzazanstagram.model.article.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import java.util.Objects;

@Embeddable
public class Image {

    @Lob
    @Column(name = "image_url", nullable = false)
    private String url;

    private Image() {
    }

    private Image(final String url) {
        this.url = url;
    }

    public static Image of(final String imageUrl) {
        return new Image(imageUrl);
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(url, image.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}