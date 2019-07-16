package techcourse.myblog.domain;

import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    boolean existsByTitle(String title);
}
