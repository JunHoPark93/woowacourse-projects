package techcourse.myblog.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepository {
    private List<Article> articles = new ArrayList<>();

    public ArticleRepository() {
        // Test 데이터 추가
        this.articles.add(new Article("testTitle", "testUrl", "testcomntents"));
        this.articles.add(new Article("ilovespring", "testUrl2", "#nice"));
    }

    public List<Article> findAll() {
        return articles;
    }

    public void add(Article article) {
        this.articles.add(article);
    }

    public Article findById(int articleId) {
        return articles.get(articleId);
    }

    public void replace(int index, Article article) {
        this.articles.set(index, article);
    }

    public void removeById(int articleId) {
        this.articles.remove(articleId);
    }
}
