package techcourse.myblog.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepository {
    private List<Article> articles = new ArrayList<>();

    public ArticleRepository() {
        // Test 데이터 추가
        this.articles.add(new Article("1번 게시물", "https://cdn.www.fastcampus.co.kr/wp-content/uploads/2019/03/%EB%B0%95%EC%9E%AC%EC%84%B1-%EA%B0%95%EC%82%AC%EB%8B%98.png", "testcomntents"));
        this.articles.add(new Article("2번 게시물", "https://t1.daumcdn.net/cfile/tistory/274E2B36579831D510", "#nice"));
        this.articles.add(new Article("3번 게시물", "", "#nice3"));
        this.articles.add(new Article("4번 게시물", "", "#nice4"));
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

    public boolean existByTitle(String title) {
        return articles.stream()
                .anyMatch(article -> article.isTitleMath(title));
    }

    public int count() {
        return (int) articles.stream()
                .count();
    }
}
