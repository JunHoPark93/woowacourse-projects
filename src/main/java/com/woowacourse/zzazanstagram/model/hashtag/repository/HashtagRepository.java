package com.woowacourse.zzazanstagram.model.hashtag.repository;

import com.woowacourse.zzazanstagram.model.hashtag.domain.Hashtag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    Optional<Hashtag> findByKeyword(String keyword);

    @Query("SELECT h FROM Hashtag h WHERE h.keyword LIKE CONCAT('%',:keyword,'%')" +
            " ORDER BY (CASE WHEN h.keyword LIKE CONCAT(:keyword,'') THEN 1" +
            " WHEN h.keyword LIKE CONCAT(:keyword,'%') THEN 2" +
            " WHEN h.keyword LIKE CONCAT('%',:keyword) THEN 4 ELSE 3 END)")
    List<Hashtag> findByKeywordContaining(@Param("keyword") String keyword, Pageable pageable);
}
