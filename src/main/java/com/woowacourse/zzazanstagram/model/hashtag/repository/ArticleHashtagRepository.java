package com.woowacourse.zzazanstagram.model.hashtag.repository;

import com.woowacourse.zzazanstagram.model.hashtag.domain.ArticleHashtag;
import com.woowacourse.zzazanstagram.model.hashtag.domain.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleHashtagRepository extends JpaRepository<ArticleHashtag, Long> {
    List<ArticleHashtag> findAllByHashtagOrderByArticleCreatedDateDesc(Hashtag hashTag);
}
