package com.woowacourse.zzazanstagram.model.hashtag.repository;

import com.woowacourse.zzazanstagram.model.hashtag.domain.TagKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagKeywordRepository extends JpaRepository<TagKeyword, Long> {
    Optional<TagKeyword> findByTagKeyword(String tagKeyword);
}
