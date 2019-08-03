package techcourse.myblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import techcourse.myblog.service.ArticleService;

@Controller
public class MainController {
    private ArticleService articleService;

    public MainController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "1") int page, Model model) {
        model.addAttribute("articles", articleService.findAll(page));
        return "index";
    }
}
