package techcourse.myblog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import techcourse.myblog.domain.Article;
import techcourse.myblog.domain.ArticleRepository;
import techcourse.myblog.service.dto.ArticleRequest;
import techcourse.myblog.service.exception.NoArticleException;

import javax.transaction.Transactional;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Page<Article> findAll(int page) {
        return articleRepository.findAll(
                PageRequest.of(page - 1, 10, Sort.by("id").descending()));
    }

    public Article save(ArticleRequest articleRequest) {
        Article article = new Article(articleRequest.getTitle(),
                articleRequest.getCoverUrl(), articleRequest.getContents());
        return articleRepository.save(article);
    }

    public Article findById(long articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new NoArticleException("게시글이 존재하지 않습니다"));
    }

    @Transactional
    public Article editArticle(ArticleRequest articleRequest, long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow(IllegalArgumentException::new);
        article.updateArticle(new Article(articleRequest.getTitle(), articleRequest.getCoverUrl(), articleRequest.getContents()));
        return article;
    }

    public void deleteById(long articleId) {
        articleRepository.deleteById(articleId);
    }
}
