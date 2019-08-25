package com.woowacourse.zzazanstagram.model.article.repository;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByIdDesc();

    @Query(value = "SELECT a FROM Article a JOIN FETCH a.author WHERE a.id < :lastArticleId AND a.author IN :members ORDER BY a.id DESC",
            countQuery = "SELECT count(a) FROM Article a JOIN a.author WHERE a.id < :lastArticleId AND a.author IN :members")
    Page<Article> findArticlesByPages(@Param("lastArticleId") Long lastArticleId, @Param("members") List<Member> members, Pageable pageable);
}
