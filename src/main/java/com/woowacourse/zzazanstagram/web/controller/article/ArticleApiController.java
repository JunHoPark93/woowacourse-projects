package com.woowacourse.zzazanstagram.web.controller.article;

import com.woowacourse.zzazanstagram.model.article.dto.ArticleMyPageResponse;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleResponse;
import com.woowacourse.zzazanstagram.model.article.service.ArticleService;
import com.woowacourse.zzazanstagram.model.member.MemberSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/articles")
@RestController
public class ArticleApiController {
    private final ArticleService articleService;

    public ArticleApiController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<ArticleResponse> getArticlePages(@RequestParam Long lastArticleId, @RequestParam int size, MemberSession memberSession) {
        return articleService.fetchArticlePages(lastArticleId, size, memberSession.getId());
    }

    @GetMapping("/mypage")
    public List<ArticleMyPageResponse> getMyPageArticles(@RequestParam Long lastArticleId, @RequestParam int size, @RequestParam String nickName) {
        return articleService.findArticleMyPageResponsesBy(lastArticleId, size, nickName);
    }
}
