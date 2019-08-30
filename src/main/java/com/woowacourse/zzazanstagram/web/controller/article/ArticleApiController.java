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

@RequestMapping("/api/articles")
@RestController
public class ArticleApiController {
    private final ArticleService articleService;

    public ArticleApiController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<List<ArticleResponse>> getArticlePages(@RequestParam Long lastArticleId, @RequestParam int size
            , MemberSession memberSession) {
        List<ArticleResponse> articleResponses = articleService.fetchArticlePages(lastArticleId, size, memberSession.getId());
        return new ResponseEntity<>(articleResponses, HttpStatus.OK);
    }

    @GetMapping("/mypage")
    public ResponseEntity<List<ArticleMyPageResponse>> getMyPageArticles(@RequestParam Long lastArticleId, @RequestParam int size
            , @RequestParam String nickName) {
        List<ArticleMyPageResponse> articleMyPageResponses = articleService.findArticleMyPageResponsesBy(lastArticleId, size, nickName);
        return new ResponseEntity<>(articleMyPageResponses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@Valid ArticleRequest dto, MemberSession memberSession) {
        articleService.save(dto, memberSession.getEmail());
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, "게시글 저장 성공"), HttpStatus.OK);
    }
}
