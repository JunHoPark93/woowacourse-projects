package com.woowacourse.zzazanstagram.web.controller.article;

import com.woowacourse.zzazanstagram.model.RequestTemplate;
import com.woowacourse.zzazanstagram.model.comment.domain.vo.CommentContents;
import com.woowacourse.zzazanstagram.model.comment.dto.CommentResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.CONTENTS;
import static org.assertj.core.api.Assertions.assertThat;

class CommentControllerTest extends RequestTemplate {
    private static final int MAX_LENGTH_OF_COMMENT = 100;
    private static final long ARTICLE_ID = 1L;

    @Test
    void 댓글_작성_테스트() {
        CommentContents commentContents = CommentContents.of(CONTENTS);

        postHeaderWithLogin("/" + ARTICLE_ID + "/comments/new")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(commentContents), CommentContents.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CommentResponse.class)
                .consumeWith(res -> assertThat(res.getResponseBody().getCommentContents()).isEqualTo(CONTENTS));
    }

    @Test
    void 댓글의_길이가_0글자인_경우() {
        CommentContents commentContents = CommentContents.of("");

        postHeaderWithLogin("/" + ARTICLE_ID + "/comments/new")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(commentContents), CommentContents.class)
                .exchange()
                .expectStatus().is4xxClientError();
    }

    @Test
    void 댓글의_길이가_1글자인_경우() {
        CommentContents commentContents = CommentContents.of("a");

        postHeaderWithLogin("/" + ARTICLE_ID + "/comments/new")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(commentContents), CommentContents.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void 댓글의_길이가_101글자인_경우() {
        StringBuilder contents = new StringBuilder();
        for (int i = 0; i <= MAX_LENGTH_OF_COMMENT; i++) {
            contents.append("a");
        }
        CommentContents commentContents = CommentContents.of(contents.toString());

        postHeaderWithLogin("/" + ARTICLE_ID + "/comments/new")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(commentContents), CommentContents.class)
                .exchange()
                .expectStatus().is4xxClientError();
    }

    @Test
    void 댓글의_길이가_100글자인_경우() {
        StringBuilder contents = new StringBuilder();
        for (int i = 0; i < MAX_LENGTH_OF_COMMENT; i++) {
            contents.append("a");
        }
        CommentContents commentContents = CommentContents.of(contents.toString());

        postHeaderWithLogin("/" + ARTICLE_ID + "/comments/new")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(commentContents), CommentContents.class)
                .exchange()
                .expectStatus().isOk();
    }
}