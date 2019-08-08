package techcourse.myblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import techcourse.myblog.domain.Article;
import techcourse.myblog.domain.User;
import techcourse.myblog.service.ArticleService;
import techcourse.myblog.service.dto.ArticleRequest;

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
    public String saveArticle(@Valid ArticleRequest articleRequest, Model model, User user) {
        Article article = articleService.save(articleRequest, user);
        model.addAttribute("article", article);
        return "redirect:/articles/" + article.getId();
    }

    @GetMapping("/{articleId}")
    public String selectArticle(@PathVariable("articleId") long articleId, Model model) {
        Article article = articleService.findById(articleId);
        model.addAttribute("article", article);
        return "article";
    }

    @GetMapping("/{articleId}/edit")
    public String edit(@PathVariable("articleId") long articleId, Model model, User user) {
        Article article = articleService.findByIdWithUser(articleId, user);
        model.addAttribute("article", article);
        return "article-edit";
    }

    @PutMapping("/{articleId}")
    public String editArticle(@PathVariable("articleId") long articleId, @ModelAttribute ArticleRequest articleRequest, User user, Model model) {
        Article article = articleService.editArticle(articleRequest, articleId, user);
        model.addAttribute("article", article);
        return "redirect:/articles/" + articleId;
    }

    @DeleteMapping("/{articleId}")
    public String deleteArticle(@PathVariable("articleId") long articleId, User user) {
        articleService.deleteById(articleId, user);
        return "redirect:/";
    }
}
