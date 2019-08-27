package com.woowacourse.zzazanstagram.model.article.service;

import com.woowacourse.zzazanstagram.model.article.dto.ArticleMyPageResponse;
import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleResponse;
import com.woowacourse.zzazanstagram.model.ddabong.service.DdabongService;
import com.woowacourse.zzazanstagram.model.follow.service.FollowService;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;
import com.woowacourse.zzazanstagram.model.member.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleFacadeService {

    private final MemberService memberService;
    private final ArticleService articleService;
    private final FollowService followService;
    private final DdabongService ddabongService;

    public ArticleFacadeService(MemberService memberService, ArticleService articleService, FollowService followService, DdabongService ddabongService) {
        this.memberService = memberService;
        this.articleService = articleService;
        this.followService = followService;
        this.ddabongService = ddabongService;
    }

    public List<ArticleResponse> getArticlePages(Long lastArticleId, int size, Long loginMemberId) {
        List<Member> followers = findFollowersByMemberId(loginMemberId);
        addLoginMemberTo(followers, loginMemberId);

        Page<Article> articles = articleService.getArticlePages(lastArticleId, followers, size);
        Member loginMember = memberService.findById(loginMemberId);

        return articles.stream().map(article -> ArticleAssembler.toDto(article, loginMember))
                .collect(Collectors.toList());
    }

    private List<Member> findFollowersByMemberId(Long memberId) {
        List<Long> followingsIds = followService.findFollowingsIds(memberId);
        return memberService.findByIds(followingsIds);
    }

    private void addLoginMemberTo(List<Member> followers, Long loginMemberId) {
        Member loginMember = memberService.findById(loginMemberId);
        followers.add(loginMember);
    }

    public List<ArticleMyPageResponse> getMyPageArticles(Long lastArticleId, int size, String nickName) {
        MemberResponse memberResponse = memberService.findByNickName(nickName);
        return articleService.getMyPageArticles(lastArticleId, size, memberResponse.getId());
    }
}
