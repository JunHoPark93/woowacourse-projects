package com.woowacourse.zzazanstagram.model.hashtag.service;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.hashtag.domain.HashTag;
import com.woowacourse.zzazanstagram.model.hashtag.repository.HashTagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HashTagService {
    private final HashTagRepository hashTagRepository;

    public HashTagService(final HashTagRepository hashTagRepository) {
        this.hashTagRepository = hashTagRepository;
    }

    public List<HashTag> save(Article article) {
        List<HashTag> hashTags = extractHashTagsFrom(article);

        return hashTagRepository.saveAll(hashTags);
    }

    private List<HashTag> extractHashTagsFrom(Article article) {
        return article.extractTagKeywords()
                .stream()
                .map(t -> new HashTag(article, t))
                .collect(Collectors.toList());
    }
}
