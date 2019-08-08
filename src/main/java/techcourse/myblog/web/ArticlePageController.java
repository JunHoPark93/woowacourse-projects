package techcourse.myblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import techcourse.myblog.service.ArticleService;
import techcourse.myblog.service.dto.UserSession;
import techcourse.myblog.service.dto.request.ArticleRequest;
import techcourse.myblog.service.dto.response.ArticleResponse;

import javax.validation.Valid;

@Controller
@RequestMapping("/articles")
public class ArticlePageController {
    private ArticleService articleService;

    public ArticlePageController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping()
    public String formArticle(Model model) {
        model.addAttribute("article", null);
        return "article-edit";
    }

    @PostMapping()
    public String saveArticle(@Valid ArticleRequest articleRequest, Model model, UserSession userSession) {
        ArticleResponse articleResponse = articleService.saveAndGet(articleRequest, userSession);
        model.addAttribute("article", articleResponse);
        return "redirect:/articles/" + articleResponse.getId();
    }

    @GetMapping("/{articleId}")
    public String selectArticle(@PathVariable("articleId") long articleId, Model model) {
        ArticleResponse articleResponse = articleService.find(articleId);
        model.addAttribute("article", articleResponse);
        return "article";
    }

    @GetMapping("/{articleId}/edit")
    public String edit(@PathVariable("articleId") long articleId, Model model, UserSession userSession) {
        ArticleResponse articleResponse = articleService.find(articleId, userSession);
        model.addAttribute("article", articleResponse);
        return "article-edit";
    }

    @PutMapping("/{articleId}")
    public String editArticle(@PathVariable("articleId") long articleId, @ModelAttribute ArticleRequest articleRequest, UserSession userSession, Model model) {
        ArticleResponse articleResponse = articleService.editAndGet(articleRequest, articleId, userSession);
        model.addAttribute("article", articleResponse);
        return "redirect:/articles/" + articleId;
    }

    @DeleteMapping("/{articleId}")
    public String deleteArticle(@PathVariable("articleId") long articleId, UserSession userSession) {
        articleService.delete(articleId, userSession);
        return "redirect:/";
    }
}
