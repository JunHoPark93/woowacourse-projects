package com.woowacourse.zzazanstagram.model.article.repository;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByIdDesc();
}
