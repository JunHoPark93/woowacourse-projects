package techcourse.myblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import techcourse.myblog.domain.Article;
import techcourse.myblog.dto.ArticleDto;
import techcourse.myblog.service.ArticleService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleController {
    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Article> articles = new ArrayList<>();
        articleService.findAll().forEach(articles::add);
        model.addAttribute("articles", articles);

        return "index";
    }

    @GetMapping("/writing")
    public String formArticle(Model model) {
        model.addAttribute("article", null);

        return "article-edit";
    }

    @PostMapping("/articles")
    public String saveArticle(@Valid ArticleDto articleDto, Model model) {
        Article article = articleService.save(articleDto);
        model.addAttribute("article", article);

        return "redirect:/articles/" + article.getId();
    }

    @GetMapping("/articles/{articleId}")
    public String selectArticle(@PathVariable("articleId") long articleId, Model model) {
        Article article = articleService.findById(articleId);
        model.addAttribute("article", article);

        return "article";
    }

    @GetMapping("/articles/{articleId}/edit")
    public String edit(@PathVariable("articleId") long articleId, Model model) {
        Article article = articleService.findById(articleId);
        model.addAttribute("article", article);

        return "article-edit";
    }

    @PutMapping("/articles/{articleId}")
    public String editArticle(@PathVariable("articleId") long articleId, @ModelAttribute ArticleDto articleDto, Model model) {
        Article article = articleService.editArticle(articleDto, articleId);
        model.addAttribute("article", article);

        return "article";
    }

    @DeleteMapping("/articles/{articleId}")
    public String deleteArticle(@PathVariable("articleId") long articleId) {
        articleService.deleteById(articleId);

        return "redirect:/";
    }
}
