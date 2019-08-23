package com.woowacourse.zzazanstagram.model.comment.repository;

import com.woowacourse.zzazanstagram.model.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
