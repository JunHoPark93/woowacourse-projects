package com.woowacourse.zzazanstagram.model.member.repository;

import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.domain.vo.Email;
import com.woowacourse.zzazanstagram.model.member.domain.vo.NickName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(Email email);

    Optional<Member> findByNickName(NickName nickName);

    boolean existsByNickNameOrEmail(NickName nickName, Email email);

    @Query("SELECT m FROM Member m WHERE m.id IN :ids")
    List<Member> findAllByIds(@Param("ids") List<Long> ids);
}
