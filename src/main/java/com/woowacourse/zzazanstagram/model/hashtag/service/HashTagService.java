package com.woowacourse.zzazanstagram.model.hashtag.service;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.hashtag.domain.HashTag;
import com.woowacourse.zzazanstagram.model.hashtag.domain.TagKeyword;
import com.woowacourse.zzazanstagram.model.hashtag.repository.HashTagRepository;
import com.woowacourse.zzazanstagram.model.hashtag.repository.TagKeywordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HashTagService {
    private final HashTagRepository hashTagRepository;
    private final TagKeywordRepository tagKeywordRepository;

    public HashTagService(final HashTagRepository hashTagRepository, final TagKeywordRepository tagKeywordRepository) {
        this.hashTagRepository = hashTagRepository;
        this.tagKeywordRepository = tagKeywordRepository;
    }

    public List<HashTag> save(Article article) {
        List<HashTag> hashTags = extractHashTagsFrom(article);

        return hashTagRepository.saveAll(hashTags);
    }

    private List<HashTag> extractHashTagsFrom(Article article) {
        return article.extractTagKeywords()
                .stream()
                .map(t -> tagKeywordRepository.findByTagKeyword(t.getTagKeyword())
                        .map(tagKeyword -> new HashTag(article, tagKeyword))
                        .orElseGet(() -> {
                            TagKeyword tagKeyword = tagKeywordRepository.save(t);
                            return new HashTag(article, tagKeyword);
                        })
                )
                .collect(Collectors.toList());
    }
  
    public List<HashTag> findAllByTagKeyword(String keyword) {
        TagKeyword tagKeyword = tagKeywordRepository.findByTagKeyword(keyword)
                .orElseThrow(() -> new HashTagException("해당 해시태그에 대한 게시글이 존재하지 않습니다."));

        return hashTagRepository.findAllByTagKeywordOrderByArticleCreatedDateDesc(tagKeyword);
    }
}
