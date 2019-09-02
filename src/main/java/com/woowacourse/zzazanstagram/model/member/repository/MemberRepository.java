package com.woowacourse.zzazanstagram.model.member.repository;

import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.domain.vo.Email;
import com.woowacourse.zzazanstagram.model.member.domain.vo.NickName;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(Email email);

    Optional<Member> findByNickName(NickName nickName);

    boolean existsByNickNameOrEmail(NickName nickName, Email email);

    List<Member> findByIdIn(List<Long> ids);

    @Query("SELECT m FROM Member m WHERE m.nickName LIKE CONCAT('%',:keyword,'%')" +
            " ORDER BY (CASE WHEN m.nickName LIKE CONCAT(:keyword,'') THEN 1" +
            " WHEN m.nickName LIKE CONCAT(:keyword,'%') THEN 2" +
            " WHEN m.nickName LIKE CONCAT('%',:keyword) THEN 4 ELSE 3 END), m.name, m.id")
    List<Member> findByNickNameContaining(@Param("keyword") String keyword, Pageable pageable);
}
