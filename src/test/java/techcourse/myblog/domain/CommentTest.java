package techcourse.myblog.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommentTest {
    @Test
    void 댓글생성() {
        assertThatCode(() -> new Comment("comments~",
                new Article("test title", "coverUrl test", "content test"),
                new User("BOB", "test@gmail.com", "PAssWord!2")))
                .doesNotThrowAnyException();
    }

    @Test
    void 댓글초기화_오류() {
        assertThrows(IllegalArgumentException.class, () -> new Comment(null,
                new Article("test title", "coverUrl test", "content test"),
                new User("BOB", "test@gmail.com", "PAssWord!2")));
    }
}
