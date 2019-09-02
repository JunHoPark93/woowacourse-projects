package com.woowacourse.zzazanstagram.web.controller.article;

import com.amazonaws.services.s3.AmazonS3;
import com.woowacourse.zzazanstagram.config.S3MockConfig;
import com.woowacourse.zzazanstagram.model.RequestTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.CONTENTS;

@Import(S3MockConfig.class)
class ArticleControllerTest extends RequestTemplate {
    private Resource file;

    @Autowired
    AmazonS3 amazonS3;

    @Autowired
    ResourceLoader resourceLoader;

    @BeforeEach
    public void setUp2() {
        file = resourceLoader.getResource("classpath:test.jpg");
    }

    @Test
    void 게시글_등록_페이지_이동_테스트() {
        getHeaderWithLogin("/articles/new")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void 게시글_등록이_되는지_테스트() {
        createArticle()
                .expectStatus().isOk();
        // TODO : jsonPath 검사하기
    }

    @Test
    void 게시글_조회_페이지_이동_테스트() {
        showArticles();
    }

    @Test
    void 게시글_삭제_성공_테스트() {
        deleteHeaderWithLogin("/articles/3")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void 게시글_삭제_권한없음_테스트() {
        deleteHeaderWithLogin("/articles/4", "abc@gmail.com", "aa1231!!")
                .exchange()
                .expectStatus().is4xxClientError();
    }

    @Test
    void 특정_해시태그가_달린_게시글_조회_테스트() {
        createArticle();

        getHeaderWithLogin("/tags/닉")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void 해시태그에_대한_게시글이_존재하지_않는_경우_예외처리() {
        getHeaderWithLogin("/tags/없는해시태그")
                .exchange()
                .expectStatus().is4xxClientError();
    }

    private WebTestClient.ResponseSpec showArticles() {
        return getHeaderWithLogin("/")
                .exchange()
                .expectStatus().isOk();
    }

    private WebTestClient.ResponseSpec createArticle() {
        return postHeaderWithLogin("/api/articles")
                .syncBody(createArticleBody().build())
                .exchange();
    }

    private MultipartBodyBuilder createArticleBody() {
        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("file", file, MediaType.parseMediaType("image/jpeg"));
        bodyBuilder.part("contents", CONTENTS);

        return bodyBuilder;
    }
}