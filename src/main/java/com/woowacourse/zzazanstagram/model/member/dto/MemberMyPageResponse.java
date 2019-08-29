package com.woowacourse.zzazanstagram.model.member.dto;

import java.util.Objects;

public class MemberMyPageResponse {
    private Long id;
    private String profileImage;
    private String nickName;
    private String name;
    private long articleNumber;
    private long followeeNumber;
    private long followerNumber;

    private MemberMyPageResponse(Long id, String profileImage, String nickName, String name, long articleNumber, long followeeNumber, long followerNumber) {
        this.id = id;
        this.profileImage = profileImage;
        this.nickName = nickName;
        this.name = name;
        this.articleNumber = articleNumber;
        this.followeeNumber = followeeNumber;
        this.followerNumber = followerNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(long articleNumber) {
        this.articleNumber = articleNumber;
    }

    public long getFolloweeNumber() {
        return followeeNumber;
    }

    public void setFolloweeNumber(long followeeNumber) {
        this.followeeNumber = followeeNumber;
    }

    public long getFollowerNumber() {
        return followerNumber;
    }

    public void setFollowerNumber(long followerNumber) {
        this.followerNumber = followerNumber;
    }


    public static final class builder {
        private Long id;
        private String profileImage;
        private String nickName;
        private String name;
        private long articleNumber;
        private long followeeNumber;
        private long followerNumber;

        private builder() {
        }

        public static builder aMemberMyPageResponse() {
            return new builder();
        }

        public builder id(Long id) {
            this.id = id;
            return this;
        }

        public builder profileImage(String profileImage) {
            this.profileImage = profileImage;
            return this;
        }

        public builder nickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public builder name(String name) {
            this.name = name;
            return this;
        }

        public builder articleNumber(long articleNumber) {
            this.articleNumber = articleNumber;
            return this;
        }

        public builder followeeNumber(long followeeNumber) {
            this.followeeNumber = followeeNumber;
            return this;
        }

        public builder followerNumber(long followerNumber) {
            this.followerNumber = followerNumber;
            return this;
        }

        public MemberMyPageResponse build() {
            return new MemberMyPageResponse(id, profileImage, nickName, name, articleNumber, followeeNumber, followerNumber);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final MemberMyPageResponse that = (MemberMyPageResponse) o;
        return articleNumber == that.articleNumber &&
                followeeNumber == that.followeeNumber &&
                followerNumber == that.followerNumber &&
                Objects.equals(id, that.id) &&
                Objects.equals(profileImage, that.profileImage) &&
                Objects.equals(nickName, that.nickName) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, profileImage, nickName, name, articleNumber, followeeNumber, followerNumber);
    }
}
