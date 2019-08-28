package com.woowacourse.zzazanstagram.model.member.service;

import com.woowacourse.zzazanstagram.model.article.service.ArticleService;
import com.woowacourse.zzazanstagram.model.follow.service.FollowService;
import com.woowacourse.zzazanstagram.model.member.dto.MemberMyPageResponse;
import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;
import org.springframework.stereotype.Service;

// TODO 제거
@Service
public class MemberFacadeService {
    private final MemberService memberService;
    private final FollowService followService;
    private final ArticleService articleService;

    public MemberFacadeService(MemberService memberService, FollowService followService, ArticleService articleService) {
        this.memberService = memberService;
        this.followService = followService;
        this.articleService = articleService;
    }

    public MemberMyPageResponse myPage(String nickName) {
        MemberResponse memberResponse = memberService.findByNickName(nickName);
        Long memberId = memberResponse.getId();
        long followerNumber = followService.countFollowers(memberId);
        long followeeNumber = followService.countFollowees(memberId);
        long articleNumber = articleService.countByAuthorId(memberId);
        String nickName1 = memberResponse.getNickName();
        String profileImage = memberResponse.getProfileImage();
        String name = memberResponse.getName();

        return MemberMyPageResponse.builder.aMemberMyPageResponse()
                .id(memberId)
                .articleNumber(articleNumber)
                .followeeNumber(followeeNumber)
                .followerNumber(followerNumber)
                .name(name)
                .nickName(nickName1)
                .profileImage(profileImage)
                .build();
    }

}
