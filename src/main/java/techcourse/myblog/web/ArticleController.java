package techcourse.myblog.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import techcourse.myblog.service.ArticleService;
import techcourse.myblog.service.dto.CommentResponse;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{articleId}/comment")
    public ResponseEntity<List<CommentResponse>> comments(@PathVariable("articleId") Long articleId) {
        List<CommentResponse> commentResponses = articleService.findAllComment(articleId);
        return new ResponseEntity<>(commentResponses, HttpStatus.OK);
    }
}
