package com.woowacourse.zzazanstagram.model.follow.dto;

public class FollowRequest {
    private Long followeeId;
    private Long followerId;

    public Long getFolloweeId() {
        return followeeId;
    }

    public void setFolloweeId(Long followeeId) {
        this.followeeId = followeeId;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }
}
