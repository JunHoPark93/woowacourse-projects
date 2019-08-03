package techcourse.myblog.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticle(Article article);

    Optional<Comment> findByCommenterAndId(User commenter, Long id);
}
