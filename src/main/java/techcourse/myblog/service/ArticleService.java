package techcourse.myblog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import techcourse.myblog.domain.Article;
import techcourse.myblog.domain.ArticleRepository;
import techcourse.myblog.domain.User;
import techcourse.myblog.service.dto.ArticleRequest;
import techcourse.myblog.service.exception.InvalidAuthorException;
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

    public Article save(ArticleRequest articleRequest, User user) {
        Article article = new Article(articleRequest.getTitle(),
                articleRequest.getCoverUrl(), articleRequest.getContents());
        article.setAuthor(user);
        return articleRepository.save(article);
    }

    public Article findById(long articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new NoArticleException("게시글이 존재하지 않습니다"));
    }

    public Article findByIdWithUser(long articleId, User user) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NoArticleException("게시글이 존재하지 않습니다"));
        checkAuthor(user, article);
        return article;
    }

    @Transactional
    public Article editArticle(ArticleRequest articleRequest, long articleId, User user) {
        Article article = articleRepository.findById(articleId).orElseThrow(IllegalArgumentException::new);
        checkAuthor(user, article);
        article.updateArticle(new Article(articleRequest.getTitle(), articleRequest.getCoverUrl(), articleRequest.getContents()));
        return article;
    }

    private void checkAuthor(User user, Article article) {
        if (!article.isAuthor(user)) {
            throw new InvalidAuthorException("작성자가 아닙니다");
        }
    }

    public void deleteById(long articleId, User user) {
        Article article = articleRepository.findById(articleId).orElseThrow(IllegalArgumentException::new);
        checkAuthor(user, article);
        articleRepository.deleteById(articleId);
    }
}
