package techcourse.myblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import techcourse.myblog.domain.Article;
import techcourse.myblog.domain.ArticleRepository;
import techcourse.myblog.dto.ArticleDto;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ArticleController {
    private ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Article> articles = articleRepository.findAll();
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
        Article article = new Article(articleDto.getTitle(), articleDto.getCoverUrl(), articleDto.getContents());
        articleRepository.add(article);
        model.addAttribute("article", article);

        return "article";
    }

    @GetMapping("/articles/{articleId}")
    public String selectArticle(@PathVariable("articleId") int articleId, Model model) {
        Article article = articleRepository.findById(articleId);
        model.addAttribute("article", article);
        model.addAttribute("id", articleId);

        return "article";
    }

    @GetMapping("/articles/{articleId}/edit")
    public String edit(@PathVariable("articleId") int articleId, Model model) {
        Article article = articleRepository.findById(articleId);
        model.addAttribute("article", article);
        model.addAttribute("id", articleId);

        return "article-edit";
    }

    @PutMapping("/articles/{articleId}")
    public String editArticle(@PathVariable("articleId") int articleId, @ModelAttribute ArticleDto articleDto, Model model) {
        Article article = new Article(articleDto.getTitle(), articleDto.getCoverUrl(), articleDto.getContents());
        articleRepository.replace(articleId, article);
        model.addAttribute("article", article);

        return "article";
    }

    @DeleteMapping("/articles/{articleId}")
    public String deleteArticle(@PathVariable("articleId") int articleId) {
        articleRepository.removeById(articleId);

        return "redirect:/";
    }
}
