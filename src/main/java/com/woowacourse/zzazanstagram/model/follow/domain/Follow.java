package com.woowacourse.zzazanstagram.model.follow.domain;

import com.woowacourse.zzazanstagram.model.common.BaseEntity;
import com.woowacourse.zzazanstagram.model.member.domain.Member;

import javax.persistence.*;

@Entity
public class Follow extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followee", nullable = false, foreignKey = @ForeignKey(name = "fk_follow_to_followee"))
    private Member followee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower", nullable = false, foreignKey = @ForeignKey(name = "fk_follow_to_follower"))
    private Member follower;

    protected Follow() {
    }

    public Follow(Member followee, Member follower) {
        this.followee = followee;
        this.follower = follower;
    }

    public Member getFollowee() {
        return followee;
    }

    public Member getFollower() {
        return follower;
    }
}
