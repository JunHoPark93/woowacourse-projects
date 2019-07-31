package techcourse.myblog.web;

import org.springframework.web.reactive.function.BodyInserters;

import static org.springframework.web.reactive.function.BodyInserters.fromFormData;

public class WebTestHelper {
    static BodyInserters.FormInserter<String> articleForm() {
        return fromFormData("title", "titleTest")
                .with("coverUrl", "coverUrlTest")
                .with("contents", "contentsTest");
    }

    static BodyInserters.FormInserter<String> signUpForm(String name, String email, String password) {
        return fromFormData("name", name)
                .with("email", email)
                .with("password", password)
                .with("reconfirmPassword", password);
    }

    static BodyInserters.FormInserter<String> loginForm(String email, String password) {
        return fromFormData("email", email)
                .with("password", password);
    }

    static BodyInserters.FormInserter<String> commentForm() {
        return fromFormData("contents", "kk")
                .with("articleId", "1");
    }
}
