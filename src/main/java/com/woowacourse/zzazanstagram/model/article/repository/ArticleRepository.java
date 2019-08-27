package com.woowacourse.zzazanstagram.model.article.repository;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByIdDesc();

    Page<Article> findByIdLessThanAndAuthorInOrderByIdDesc(Long id, List<Member> authors, Pageable pageable);

    Page<Article> findByIdLessThanAndAuthorIdEqualsOrderByIdDesc(Long id, Long authorId, Pageable pageable);

    long countArticleByAuthorId(Long id);
}
