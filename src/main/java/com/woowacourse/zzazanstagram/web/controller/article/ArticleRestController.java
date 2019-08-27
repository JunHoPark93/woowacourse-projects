package com.woowacourse.zzazanstagram.web.controller.article;

import com.woowacourse.zzazanstagram.model.article.dto.ArticleMyPageResponse;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleResponse;
import com.woowacourse.zzazanstagram.model.article.service.ArticleFacadeService;
import com.woowacourse.zzazanstagram.model.member.MemberSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleRestController {

    private final ArticleFacadeService articleFacadeService;

    public ArticleRestController(ArticleFacadeService articleFacadeService) {
        this.articleFacadeService = articleFacadeService;
    }

    @GetMapping("/articles")
    public List<ArticleResponse> getArticlePages(@RequestParam(value = "lastArticleId") Long lastArticleId
            , @RequestParam(value = "size") int size, MemberSession memberSession) {

        return articleFacadeService.getArticlePages(lastArticleId, size, memberSession.getId());
    }

    @GetMapping("/mypage/articles")
    public List<ArticleMyPageResponse> getMyPageArticles(@RequestParam(value = "lastArticleId") Long lastArticleId
            , @RequestParam(value = "size") int size, @RequestParam(value = "nickName") String nickName) {
        return articleFacadeService.getMyPageArticles(lastArticleId, size, nickName);
    }


}
