package com.woowacourse.zzazanstagram.model.article.service;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.article.domain.vo.Contents;
import com.woowacourse.zzazanstagram.model.article.domain.vo.Image;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleRequest;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleResponse;
import com.woowacourse.zzazanstagram.model.article.repository.ArticleRepository;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.service.MemberService;
import com.woowacourse.zzazanstagram.util.S3Uploader;
import mockit.Deencapsulation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class ArticleServiceTest {
    private MultipartFile file;
    private Image image;
    private Contents contents;
    private Member member;

    @InjectMocks // @Mock, @Spy가 붙은 목 객체를 자신의 멤버 클래스와 일치하면 주입시킨다
    private ArticleService articleService;

    @Mock
    private MemberService memberService;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private S3Uploader s3Uploader;

    @BeforeEach
    void setUp() {
        file = new MockMultipartFile("any_picture.jpg", "blahblah".getBytes());
        image = Image.of(IMAGE_URL);
        contents = Contents.of(CONTENTS);
        member = Member.MemberBuilder.aMember()
                .email(EMAIL)
                .name(NAME)
                .nickName(NICKNAME)
                .password(PASSWORD)
                .profile(IMAGE_URL)
                .build();
    }

    @Test
    public void 모든_게시글들이_select되는지_테스트() { // TODO 나중에 팔로우 중인 게시글만 뽑아내도록 바꿔야 함
        // given
        Article article = new Article(image, contents, member);
        ArticleResponse response = Deencapsulation.invoke(ArticleAssembler.class, "toDto", article);
        List<Article> articles = Arrays.asList(article, article, article);

        given(articleRepository.findAllByOrderByIdDesc()).willReturn(articles);

        // when
        List<ArticleResponse> articleResponses = articleService.getArticleResponses();

        // then
        assertThat(articleResponses).isEqualTo(Arrays.asList(response, response, response));
    }

    @Test
    public void save() {
        // given
        ArticleRequest articleRequest = new ArticleRequest();
        articleRequest.setFile(file);
        articleRequest.setContents(CONTENTS);
        articleRequest.setHashTag(HASHTAG);

        Article article = Deencapsulation.invoke(ArticleAssembler.class, "toEntity", articleRequest, IMAGE_URL, member);
        given(memberService.findByEmail(EMAIL)).willReturn(member);
        given(articleRepository.save(article)).willReturn(article);
        given(s3Uploader.upload(file, "dirName")).willReturn("dirName/blahblah");

        // when
        articleService.save(articleRequest, EMAIL);

        // then
        verify(articleRepository, times(1)).save(article);
    }

}