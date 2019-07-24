package techcourse.myblog.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class ArticleTest {
    @Test
    void 게시글_정상_생성() {
        assertThatCode(() -> new Article("test title", "coverUrl test", "content test"))
                .doesNotThrowAnyException();
    }
}
