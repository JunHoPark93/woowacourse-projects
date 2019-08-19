package com.woowacourse.zzazanstagram.model.article.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Image {

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
}