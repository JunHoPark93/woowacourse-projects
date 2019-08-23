package com.woowacourse.zzazanstagram.model.member.dto;

public class MemberRelationResponse {
    private boolean isFollower;
    private boolean isFollowing;

    public MemberRelationResponse(boolean isFollower, boolean isFollowing) {
        this.isFollower = isFollower;
        this.isFollowing = isFollowing;
    }

    public boolean isFollower() {
        return isFollower;
    }

    public void setFollower(boolean follower) {
        isFollower = follower;
    }

    public boolean isFollowing() {
        return isFollowing;
    }

    public void setFollowing(boolean following) {
        isFollowing = following;
    }
}
