package techcourse.myblog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import techcourse.myblog.domain.Article;
import techcourse.myblog.domain.ArticleRepository;
import techcourse.myblog.dto.ArticleDto;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);

        return "index";
    }

    @GetMapping("/writing")
    public String articleForm(Model model) {
        model.addAttribute("article", null);
        return "article-edit";
    }

    @PostMapping("/articles")
    public String articleSave(@ModelAttribute ArticleDto articleDto, Model model) {
        Article article = new Article(articleDto.getTitle(), articleDto.getCoverUrl(), articleDto.getContents());
        articleRepository.add(article);
        model.addAttribute("article", article);

        return "article";
    }

    @GetMapping("/articles/{articleId}")
    public String articleSelect(@PathVariable("articleId") int articleId, Model model) {
        Article article = articleRepository.findById(articleId);
        model.addAttribute("article", article);
        model.addAttribute("id", articleId);

        return "article";
    }

    @GetMapping("/articles/{articleId}/edit")
    public String articleModify(@PathVariable("articleId") int articleId, Model model) {
        Article article = articleRepository.findById(articleId);
        model.addAttribute("article", article);
        model.addAttribute("id", articleId);

        return "article-edit";
    }

    @PutMapping("/articles/{articleId}")
    public String articlePut(@PathVariable("articleId") int articleId, @ModelAttribute ArticleDto articleDto, Model model) {
        Article article = new Article(articleDto.getTitle(), articleDto.getCoverUrl(), articleDto.getContents());
        articleRepository.replace(articleId, article);

        model.addAttribute("article", article);
        return "article";
    }
}
