package com.woowacourse.zzazanstagram.web.controller.article;

import com.woowacourse.zzazanstagram.model.article.dto.ArticleMyPageResponse;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleRequest;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleResponse;
import com.woowacourse.zzazanstagram.model.article.service.ArticleService;
import com.woowacourse.zzazanstagram.model.member.MemberSession;
import com.woowacourse.zzazanstagram.web.message.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// TODO restcontroller는 responseentity로 리턴 하자고 하지 않았나?
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

    @PostMapping
    public ResponseEntity<ApiResponse> create(@Valid ArticleRequest dto, MemberSession memberSession) {
        articleService.save(dto, memberSession.getEmail());
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "게시글 저장 성공"), HttpStatus.OK);
    }
}
