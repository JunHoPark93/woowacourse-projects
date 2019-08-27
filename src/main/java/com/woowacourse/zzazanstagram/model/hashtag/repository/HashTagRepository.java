package com.woowacourse.zzazanstagram.model.hashtag.repository;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.hashtag.domain.HashTag;
import com.woowacourse.zzazanstagram.model.hashtag.domain.TagKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HashTagRepository extends JpaRepository<HashTag, Long> {
    List<HashTag> findAllByTagKeywordOrderByArticleCreatedDateDesc(TagKeyword tagKeyword);
    List<HashTag> findAllByArticle(Article article);
    List<HashTag> findAllByTagKeyword(TagKeyword tagKeyword);
}
