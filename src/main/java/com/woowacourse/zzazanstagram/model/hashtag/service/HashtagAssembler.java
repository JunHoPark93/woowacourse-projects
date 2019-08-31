package com.woowacourse.zzazanstagram.model.hashtag.service;

import com.woowacourse.zzazanstagram.model.hashtag.domain.Hashtag;
import com.woowacourse.zzazanstagram.model.hashtag.dto.HashtagResponse;

public class HashtagAssembler {
    public static HashtagResponse toDto(Hashtag hashtag) {
        return new HashtagResponse(hashtag.getKeyword());
    }
}
