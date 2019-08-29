package com.woowacourse.zzazanstagram.model.article.domain;

import com.woowacourse.zzazanstagram.model.article.domain.vo.Contents;
import com.woowacourse.zzazanstagram.model.article.domain.vo.Image;
import com.woowacourse.zzazanstagram.model.article.exception.ArticleAuthenticationException;
import com.woowacourse.zzazanstagram.model.comment.domain.Comment;
import com.woowacourse.zzazanstagram.model.comment.domain.vo.CommentContents;
import com.woowacourse.zzazanstagram.model.ddabong.domain.Ddabong;
import com.woowacourse.zzazanstagram.model.hashtag.domain.Hashtag;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
class ArticleTest {
    private Image image;
    private Contents contents;
    private Article article;
    private Member author;

    @BeforeEach
    void setUp() {
        image = Image.of(IMAGE_URL);
        contents = Contents.of(CONTENTS);
        author = Member.MemberBuilder.aMember()
                .nickName("nickName")
                .name("name")
                .email("test@test.com")
                .password("password1!")
                .profile("https://image.shutterstock.com/image-photo/600w-1029171697.jpg")
                .build();
        article = new Article(image, contents, author);
    }

    @Test
    void Article_객체_생성() {
        assertThat(article).isEqualTo(new Article(image, contents, author));
    }

    @Test
    void getCommentsCount() {
        long commentCount = 3;

        List<Comment> comments = new ArrayList<>();
        for (int i = 0; i < commentCount; i++) {
            comments.add(new Comment(CommentContents.of(COMMENT_CONTENTS), article, author));
        }

        ReflectionTestUtils.setField(article, "comments", comments);

        assertThat(article.getCommentsCount()).isEqualTo(commentCount);
    }

    @Test
    void countClickedDdabong() {
        List<Ddabong> ddabongs = new ArrayList<>();
        Ddabong ddabong = new Ddabong(article, author);
        ddabongs.add(ddabong);

        ReflectionTestUtils.setField(article, "ddabongs", ddabongs);

        assertThat(article.countClickedDdabong()).isEqualTo(1L);

        ddabong.changeClicked();
        assertThat(article.countClickedDdabong()).isEqualTo(0L);
    }

    @Test
    void checkAuthentication() {
        Member anotherMember = Member.MemberBuilder.aMember()
                .nickName("another")
                .name("name")
                .email("test@another.com")
                .password("password1!")
                .profile("https://image.shutterstock.com/image-photo/600w-1029171697.jpg")
                .build();

        assertDoesNotThrow(() -> article.checkAuthentication(author));
        assertThrows(ArticleAuthenticationException.class, () -> article.checkAuthentication(anotherMember));
    }

    @Test
    void isDdabongClicked() {
        List<Ddabong> ddabongs = new ArrayList<>();
        Ddabong ddabong = new Ddabong(article, author);
        ddabongs.add(ddabong);

        ReflectionTestUtils.setField(article, "ddabongs", ddabongs);

        Member anotherMember = Member.MemberBuilder.aMember()
                .nickName("another")
                .name("name")
                .email("test@another.com")
                .password("password1!")
                .profile("https://image.shutterstock.com/image-photo/600w-1029171697.jpg")
                .build();

        assertThat(article.isDdabongClicked(author)).isTrue();
        assertThat(article.isDdabongClicked(anotherMember)).isFalse();
    }

    @Test
    void extractTagKeywords() {
        List<String> expected = Arrays.asList("아이크", "닉", "뚱이", "제이", "에헴");

        assertThat(article.extractTagKeywords()
                .stream()
                .map(Hashtag::getKeyword)
                .collect(Collectors.toList()))
                .isEqualTo(expected);
    }
}