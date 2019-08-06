package techcourse.myblog.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static techcourse.myblog.web.WebTestHelper.*;

@AutoConfigureWebTestClient
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ArticleControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    private String cookie;

    @BeforeEach
    void setUp() {
        // 회원가입
        webTestClient.post().uri("/users")
                .body(signUpForm("CU", "love@gmail.com", "PassWord!1"))
                .exchange()
                .expectStatus()
                .isFound()
        ;

        cookie = getCookie("love@gmail.com");

        // 글쓰기
        webTestClient.post()
                .uri("/articles")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(articleForm("title", "http://woowabros.github.io/img/2019-02-08/techcourse_poster.jpeg", "contents"))
                .header("Cookie", cookie)
                .exchange()
                .expectStatus().isFound();

    }

    @Test
    void 글쓰기_접근() {
        webTestClient.get().uri("/articles")
                .header("Cookie", cookie)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void 게시글조회() {
        webTestClient.get()
                .uri("/articles/" + "1")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void 존재하지_않는_게시글_조회_에러처리() {
        webTestClient.get()
                .uri("/articles/" + "2")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void 게시글삭제() {
        webTestClient.delete().uri("/articles/1")
                .header("Cookie", cookie)
                .exchange()
                .expectHeader()
                .valueMatches("location", ".*/")
                .expectStatus()
                .is3xxRedirection();
    }

    @Test
    void 다른사용자가_게시글수정페이지접근_시도_메인페이지로_이동() {
        webTestClient.get().uri("/logout")
                .exchange();

        // 다른 사람 회원가입
        webTestClient.post().uri("/users")
                .body(signUpForm("Jay", "test@gmail.com", "PassWord!1"))
                .exchange()
                .expectStatus()
                .isFound()
        ;

        String cookie = getCookie("test@gmail.com");

        webTestClient.get().uri("/articles/1/edit")
                .header("Cookie", cookie)
                .exchange()
                .expectHeader()
                .valueMatches("location", ".*/")
                .expectStatus()
                .isFound()
        ;
    }


    @Test
    void 다른사용자가_게시글수정_시도_게시글페이지로_이동() {
        webTestClient.get().uri("/logout")
                .exchange();

        // 다른 사람 회원가입
        webTestClient.post().uri("/users")
                .body(signUpForm("Jay", "test@gmail.com", "PassWord!1"))
                .exchange()
                .expectStatus()
                .isFound()
        ;

        String cookie = getCookie("test@gmail.com");

        webTestClient.put().uri("/articles/1")
                .header("Cookie", cookie)
                .exchange()
                .expectHeader()
                .valueMatches("location", ".*/")
                .expectStatus()
                .isFound()
        ;
    }

    @Test
    void 내가쓴글_수정_시도() {
        webTestClient.put().uri("/articles/1")
                .header("Cookie", cookie)
                .body(articleForm("modifyingTitle", "http://woowabros.github.io/img/2019-02-08/techcourse_poster.jpeg", "modifyingContents"))
                .exchange()
                .expectStatus()
                .isFound();
    }

    @Test
    void 존재하지않는_커버이미지로_게시글저장_메인페이지로_리다이렉트() {
        webTestClient.post().uri("/articles")
                .header("Cookie", cookie)
                .body(articleForm("modifyingTitle", "invalidUrl", "modifyingContents"))
                .exchange()
                .expectHeader()
                .valueMatches("location", ".*/")
                .expectStatus()
                .isFound();
    }

    private String getCookie(String email) {
        return webTestClient.post().uri("/login")
                .body(loginForm(email, "PassWord!1"))
                .exchange()
                .expectStatus()
                .isFound()
                .returnResult(String.class)
                .getResponseHeaders()
                .getFirst("Set-Cookie");
    }
}
