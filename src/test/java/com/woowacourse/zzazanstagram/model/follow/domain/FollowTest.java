package com.woowacourse.zzazanstagram.model.follow.domain;

import com.woowacourse.zzazanstagram.model.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

class FollowTest {
    private Follow follow;

    @BeforeEach
    void setUp() {
        Member followee = Member.MemberBuilder.aMember().build();
        Member follower = Member.MemberBuilder.aMember().build();

        ReflectionTestUtils.setField(followee, "id", 1L);
        ReflectionTestUtils.setField(follower, "id", 2L);

        follow = new Follow(followee, follower);

        ReflectionTestUtils.setField(follow, "id", 1L);
    }

    @Test
    void create() {
        Follow follow = new Follow(Member.MemberBuilder.aMember().build(), Member.MemberBuilder.aMember().build());
        ReflectionTestUtils.setField(follow, "id", 1L);

        assertThat(follow).isEqualTo(this.follow);
    }

    @Test
    void getFollowee() {
        Member followee = Member.MemberBuilder.aMember().build();
        ReflectionTestUtils.setField(followee, "id", 1L);

        assertThat(follow.getFollowee()).isEqualTo(followee);
    }

    @Test
    void getFollower() {
        Member follower = Member.MemberBuilder.aMember().build();
        ReflectionTestUtils.setField(follower, "id", 2L);

        assertThat(follow.getFollower()).isEqualTo(follower);
    }
}