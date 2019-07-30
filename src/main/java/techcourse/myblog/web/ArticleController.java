package techcourse.myblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import techcourse.myblog.domain.Article;
import techcourse.myblog.domain.User;
import techcourse.myblog.service.ArticleService;
import techcourse.myblog.service.CommentService;
import techcourse.myblog.service.dto.ArticleRequest;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ArticleController {
    private ArticleService articleService;
    private CommentService commentService;

    public ArticleController(ArticleService articleService, CommentService commentService) {
        this.articleService = articleService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "1") int page, Model model) {
        model.addAttribute("articles", articleService.findAll(page));
        return "index";
    }

    @GetMapping("/writing")
    public String formArticle(Model model) {
        model.addAttribute("article", null);
        return "article-edit";
    }

    @PostMapping("/articles")
    public String saveArticle(@Valid ArticleRequest articleRequest, Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Article article = articleService.save(articleRequest, user);
        model.addAttribute("article", article);
        return "redirect:/articles/" + article.getId();
    }

    @GetMapping("/articles/{articleId}")
    public String selectArticle(@PathVariable("articleId") long articleId, Model model) {
        Article article = articleService.findById(articleId);
        model.addAttribute("article", article);
        model.addAttribute("comments", commentService.findByArticle(article));
        return "article";
    }

    @GetMapping("/articles/{articleId}/edit")
    public String edit(@PathVariable("articleId") long articleId, Model model, HttpSession httpSession) {
        Article article = articleService.findByIdWithUser(articleId, (User) httpSession.getAttribute("user"));
        model.addAttribute("article", article);
        return "article-edit";
    }

    @PutMapping("/articles/{articleId}")
    public String editArticle(@PathVariable("articleId") long articleId, @ModelAttribute ArticleRequest articleRequest, HttpSession httpSession, Model model) {
        Article article = articleService.editArticle(articleRequest, articleId, (User) httpSession.getAttribute("user"));
        model.addAttribute("article", article);
        return "redirect:/articles/" + articleId;
    }

    @DeleteMapping("/articles/{articleId}")
    public String deleteArticle(@PathVariable("articleId") long articleId, HttpSession httpSession) {
        articleService.deleteById(articleId, (User) httpSession.getAttribute("user"));
        return "redirect:/";
    }
}
