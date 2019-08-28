package com.woowacourse.zzazanstagram.model.follow.dto;

import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;

public class FollowResult {
    private MemberResponse followee;
    private MemberResponse follower;
    private boolean isFollow;

    public FollowResult(MemberResponse followee, MemberResponse follower, boolean isFollow) {
        this.followee = followee;
        this.follower = follower;
        this.isFollow = isFollow;
    }

    public MemberResponse getFollowee() {
        return followee;
    }

    public void setFollowee(MemberResponse followee) {
        this.followee = followee;
    }

    public MemberResponse getFollower() {
        return follower;
    }

    public void setFollower(MemberResponse follower) {
        this.follower = follower;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }

    public String getFollowerNickName() {
        return this.follower.getNickName();
    }

    public String getFolloweeNickName() {
        return this.followee.getNickName();
    }
}
