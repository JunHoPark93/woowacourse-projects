package techcourse.myblog.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepository {
    private List<Article> articles = new ArrayList<>();

    public ArticleRepository() {
        this.articles.add(new Article("testTitle", "testUrl", "testcomntents"));
        this.articles.add(new Article("testTitle2", "testUrl2", "testcomntents2"));
    }

    public List<Article> findAll() {
        return articles;
    }

    public void add(Article article) {
        this.articles.add(article);
    }
}
