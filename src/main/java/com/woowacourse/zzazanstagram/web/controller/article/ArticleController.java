package com.woowacourse.zzazanstagram.web.controller.article;

import com.woowacourse.zzazanstagram.model.article.dto.ArticleRequest;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleResponse;
import com.woowacourse.zzazanstagram.model.article.service.ArticleService;
import com.woowacourse.zzazanstagram.model.member.MemberSession;
import com.woowacourse.zzazanstagram.model.member.dto.MemberMyPageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

// TODO requestmapping 분리
@Controller
public class ArticleController {
    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);
    private static final String TAG = "[ArticleController]";

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles/new")
    public String createArticle() {
        return "article-edit";
    }

    @GetMapping("/articles/{articleId}")
    public String getArticle(@PathVariable Long articleId, MemberSession memberSession, Model model) {
        ArticleResponse articleResponse = articleService.getArticle(articleId, memberSession.getEmail());
        model.addAttribute("article", articleResponse);

        return "article";
    }

    @PostMapping("/articles")
    public String create(@Valid ArticleRequest dto, MemberSession memberSession) {
        articleService.save(dto, memberSession.getEmail());
        return "redirect:/";
    }

    @DeleteMapping("/articles/{articleId}")
    public ResponseEntity<String> deleteArticle(@PathVariable Long articleId, MemberSession memberSession) {
        articleService.delete(articleId, memberSession.getEmail());
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @GetMapping("/tags/{tagKeyword}")
    public String getArticlesByTagKeyword(@PathVariable String tagKeyword, MemberSession memberSession, Model model) {
        List<ArticleResponse> articleResponses = articleService.findArticleByTagKeyword(tagKeyword, memberSession.getId());
        model.addAttribute("articles", articleResponses);

        log.info("{} getArticlesByTagKeyword() >> {}", TAG, tagKeyword);

        return "tags";
    }

    @GetMapping("/members/{nickname}")
    public String myPage(@PathVariable("nickname") String nickName, Model model) {
        MemberMyPageResponse memberMyPageResponse = articleService.myPage(nickName);
        model.addAttribute("member", memberMyPageResponse);
        return "mypage";
    }
}
