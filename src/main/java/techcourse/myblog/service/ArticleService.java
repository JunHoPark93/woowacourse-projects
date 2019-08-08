package techcourse.myblog.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import techcourse.myblog.domain.Article;
import techcourse.myblog.domain.ArticleRepository;
import techcourse.myblog.domain.User;
import techcourse.myblog.service.dto.request.ArticleRequest;
import techcourse.myblog.service.dto.response.ArticleResponse;
import techcourse.myblog.service.exception.InvalidAuthorException;
import techcourse.myblog.service.exception.NoArticleException;
import techcourse.myblog.service.exception.ResourceNotFoundException;

import javax.transaction.Transactional;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;
    private ModelMapper modelMapper;

    public ArticleService(ArticleRepository articleRepository, ModelMapper modelMapper) {
        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;
    }

    public Page<Article> findAll(int page) {
        return articleRepository.findAll(
                PageRequest.of(page - 1, 10, Sort.by("id").descending()));
    }

    public ArticleResponse saveAndGet(ArticleRequest articleRequest, User user) {
        Article article = createArticle(articleRequest);
        article.setAuthor(user);
        articleRepository.save(article);
        return modelMapper.map(article, ArticleResponse.class);
    }

    private Article createArticle(ArticleRequest articleRequest) {
        try {
            return new Article(articleRequest.getTitle(), articleRequest.getCoverUrl(), articleRequest.getContents());
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    Article findById(long articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new NoArticleException("게시글이 존재하지 않습니다"));
    }

    @Transactional
    public ArticleResponse editAndGet(ArticleRequest articleRequest, long articleId, User user) {
        Article article = articleRepository.findById(articleId).orElseThrow(IllegalArgumentException::new);
        checkAuthor(user, article);
        article.updateArticle(new Article(articleRequest.getTitle(), articleRequest.getCoverUrl(), articleRequest.getContents()));

        return modelMapper.map(article, ArticleResponse.class);
    }

    private void checkAuthor(User user, Article article) {
        if (!article.isAuthor(user)) {
            throw new InvalidAuthorException("작성자가 아닙니다");
        }
    }

    public void delete(long articleId, User user) {
        Article article = articleRepository.findById(articleId).orElseThrow(IllegalArgumentException::new);
        checkAuthor(user, article);
        articleRepository.deleteById(articleId);
    }

    public ArticleResponse find(long articleId) {
        return modelMapper.map(articleRepository.findById(articleId)
                .orElseThrow(() -> new NoArticleException("게시글이 존재하지 않습니다")), ArticleResponse.class);
    }

    public ArticleResponse find(long articleId, User user) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NoArticleException("게시글이 존재하지 않습니다"));
        checkAuthor(user, article);
        return modelMapper.map(article, ArticleResponse.class);
    }
}
