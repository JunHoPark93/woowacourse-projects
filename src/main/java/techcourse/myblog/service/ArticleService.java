package techcourse.myblog.service;

import org.springframework.stereotype.Service;
import techcourse.myblog.domain.Article;
import techcourse.myblog.domain.ArticleRepository;
import techcourse.myblog.dto.ArticleDto;

import javax.transaction.Transactional;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Iterable<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article save(ArticleDto articleDto) {
        Article article = new Article(articleDto.getTitle(),
                articleDto.getCoverUrl(), articleDto.getContents());
        return articleRepository.save(article);
    }

    public Article findById(long articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    public Article editArticle(ArticleDto articleDto, long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(IllegalArgumentException::new);
        article.updateArticle(articleDto.toArticle());
        return article;
    }

    public void deleteById(long articleId) {
        articleRepository.deleteById(articleId);
    }
}
