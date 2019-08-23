package com.woowacourse.zzazanstagram.model.ddabong.service;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.article.service.ArticleService;
import com.woowacourse.zzazanstagram.model.ddabong.domain.Ddabong;
import com.woowacourse.zzazanstagram.model.ddabong.dto.DdabongResponse;
import com.woowacourse.zzazanstagram.model.ddabong.repository.DdabongRepository;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public DdabongResponse toggleDdabong(Long articleId, String memberEmail) {
        Article article = articleService.findArticleById(articleId);
        Member member = memberService.findByEmail(memberEmail);

        return ddabongRepository.findByArticleAndMember(article, member).map(ddabong -> {
            ddabong.changeClicked();
            return getDdabongResponse(article, ddabong);
        }).orElseGet(() -> {
            Ddabong createdDdabong = new Ddabong(article, member);
            ddabongRepository.save(createdDdabong);
            return getDdabongResponse(article, createdDdabong);
        });
    }

    private DdabongResponse getDdabongResponse(Article article, Ddabong createdDdabong) {
        return DdabongAssembler.toDto(article.getDdabongCount(), createdDdabong.isClicked());
    }
}
