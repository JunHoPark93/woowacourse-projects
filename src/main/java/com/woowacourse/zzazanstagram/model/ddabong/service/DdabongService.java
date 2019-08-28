package com.woowacourse.zzazanstagram.model.ddabong.service;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.article.service.ArticleService;
import com.woowacourse.zzazanstagram.model.ddabong.domain.Ddabong;
import com.woowacourse.zzazanstagram.model.ddabong.dto.DdabongMemberResponse;
import com.woowacourse.zzazanstagram.model.ddabong.dto.DdabongToggleResponse;
import com.woowacourse.zzazanstagram.model.ddabong.repository.DdabongRepository;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DdabongService {
    private final DdabongRepository ddabongRepository;
    private final ArticleService articleService;
    private final MemberService memberService;

    public DdabongService(DdabongRepository ddabongRepository, ArticleService articleService, MemberService memberService) {
        this.ddabongRepository = ddabongRepository;
        this.articleService = articleService;
        this.memberService = memberService;
    }


    @Transactional
    public DdabongToggleResponse findDdabongToggleResponseBy(Long articleId, String memberEmail) {
        Article article = articleService.findById(articleId);
        Member member = memberService.findByEmail(memberEmail);

        return ddabongRepository.findByArticleAndMember(article, member)
                .map(ddabong -> {
                    ddabong.changeClicked();
                    return assembleToDdabongToggleResponseBy(article, ddabong);
                })
                .orElseGet(() -> {
                    Ddabong createdDdabong = new Ddabong(article, member);
                    ddabongRepository.save(createdDdabong);
                    return assembleToDdabongToggleResponseBy(article, createdDdabong);
                });
    }

    private DdabongToggleResponse assembleToDdabongToggleResponseBy(Article article, Ddabong createdDdabong) {
        return DdabongAssembler.toDto(article.countClickedDdabong(), createdDdabong.isClicked());
    }

    public DdabongMemberResponse findDdabongMemberResponseBy(Long articleId) {
        Article article = articleService.findById(articleId);
        return DdabongAssembler.toDto(article.getDdabongs());
    }
}
