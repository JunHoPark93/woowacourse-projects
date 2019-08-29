package com.woowacourse.zzazanstagram.model.article.service;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.article.domain.vo.Contents;
import com.woowacourse.zzazanstagram.model.article.domain.vo.Image;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleMyPageResponse;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleRequest;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleResponse;
import com.woowacourse.zzazanstagram.model.article.exception.ArticleException;
import com.woowacourse.zzazanstagram.model.article.repository.ArticleRepository;
import com.woowacourse.zzazanstagram.model.follow.service.FollowService;
import com.woowacourse.zzazanstagram.model.hashtag.domain.ArticleHashtag;
import com.woowacourse.zzazanstagram.model.hashtag.service.ArticleHashtagService;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.dto.MemberMyPageResponse;
import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;
import com.woowacourse.zzazanstagram.model.member.service.MemberAssembler;
import com.woowacourse.zzazanstagram.model.member.service.MemberService;
import com.woowacourse.zzazanstagram.util.S3Uploader;
import mockit.Deencapsulation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ArticleServiceTest {
    private static final long ARTICLE_ID = 1L;
    private static final long FOLLOWER_ID = 2L;
    private static final long MEMBER_ID = 1L;
    private MultipartFile file;
    private Image image;
    private Contents contents;
    private Member member;
    private List<ArticleHashtag> articleHashtags = new ArrayList<>();

    @InjectMocks // @Mock, @Spy가 붙은 목 객체를 자신의 멤버 클래스와 일치하면 주입시킨다
    private ArticleService articleService;

    @Mock
    private MemberService memberService;

    @Mock
    private ArticleHashtagService articleHashtagService;

    @Mock
    private FollowService followService;

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
    public void save() {
        // given
        ArticleRequest articleRequest = new ArticleRequest(file, CONTENTS);

        Article article = Deencapsulation.invoke(ArticleAssembler.class, "toEntity", articleRequest, IMAGE_URL, member);
        given(memberService.findByEmail(EMAIL)).willReturn(member);
        given(articleRepository.save(article)).willReturn(article);
        given(s3Uploader.upload(file, "dirName")).willReturn("dirName/blahblah");
        given(articleHashtagService.save(article)).willReturn(articleHashtags);

        // when
        articleService.save(articleRequest, EMAIL);

        // then
        verify(articleRepository, times(1)).save(article);
    }

    @Test
    void findArticleResponseBy() {
        Article article = new Article(image, contents, member);

        given(memberService.findByEmail(EMAIL)).willReturn(member);
        given(articleRepository.findById(ARTICLE_ID)).willReturn(Optional.of(article));

        ArticleResponse articleResponse = Deencapsulation.invoke(ArticleAssembler.class, "toDto", article, member);
        assertThat(articleService.findArticleResponseBy(ARTICLE_ID, EMAIL)).isEqualTo(articleResponse);
    }

    @Test
    void findById_찾는_아이디_article_없는_예외_발생() {
        given(articleRepository.findById(ARTICLE_ID)).willReturn(Optional.empty());

        assertThrows(ArticleException.class, () -> articleService.findById(ARTICLE_ID));
    }

    @Test
    void fetchArticlePages() {
        long lastArticleId = 3L;
        int size = 1;
        List<Long> followingsIds = Collections.singletonList(FOLLOWER_ID);
        List<Member> followers = (List<Member>) mock(List.class);
        PageRequest pageRequest = PageRequest.of(0, size);
        Article article = new Article(image, contents, member);
        Page<Article> articles = new PageImpl<>(Collections.singletonList(article));

        given(followService.findFollowingsIds(MEMBER_ID)).willReturn(followingsIds);
        given(memberService.findAllByIds(followingsIds)).willReturn(followers);

        given(memberService.findById(MEMBER_ID)).willReturn(member);
        given(followers.add(member)).willReturn(true);

        given(articleRepository.findByIdLessThanAndAuthorInOrderByIdDesc(lastArticleId, followers, pageRequest)).willReturn(articles);

        ArticleResponse articleResponse = Deencapsulation.invoke(ArticleAssembler.class, "toDto", article, member);
        assertThat(articleService.fetchArticlePages(lastArticleId, size, MEMBER_ID)).isEqualTo(Collections.singletonList(articleResponse));
    }

    @Test
    void deleteById() {
        Article article = mock(Article.class);

        given(articleRepository.findById(MEMBER_ID)).willReturn(Optional.of(article));
        given(memberService.findByEmail(EMAIL)).willReturn(member);

        articleService.deleteById(ARTICLE_ID, EMAIL);

        verify(articleRepository, times(1)).delete(article);
    }

    @Test
    void findArticleMyPageResponsesBy() {
        long lastArticleId = 3L;
        int size = 1;
        PageRequest pageRequest = PageRequest.of(0, size);
        Article article = new Article(image, contents, member);
        Page<Article> articles = new PageImpl<>(Collections.singletonList(article));

        MemberResponse memberResponse = mock(MemberResponse.class);
        given(memberService.findMemberResponseByNickName(NICKNAME)).willReturn(memberResponse);
        given(memberResponse.getId()).willReturn(MEMBER_ID);

        given(articleRepository.findByIdLessThanAndAuthorIdEqualsOrderByIdDesc(lastArticleId, MEMBER_ID, pageRequest))
                .willReturn(articles);

        ArticleMyPageResponse articleMyPageResponse = Deencapsulation.invoke(ArticleAssembler.class, "toMyPageDto", article);
        assertThat(articleService.findArticleMyPageResponsesBy(lastArticleId, size, NICKNAME)).isEqualTo(Collections.singletonList(articleMyPageResponse));
    }

    @Test
    void findArticleResponsesBy() {
        given(memberService.findById(MEMBER_ID)).willReturn(member);
        ArticleHashtag articleHashtag = mock(ArticleHashtag.class);
        given(articleHashtagService.findAllByHashtag(KEYWORD_1)).willReturn(Collections.singletonList(articleHashtag));

        Article article = new Article(image, contents, member);
        given(articleHashtag.getArticle()).willReturn(article);

        ArticleResponse articleResponse = Deencapsulation.invoke(ArticleAssembler.class, "toDto", article, member);
        assertThat(articleService.findArticleResponsesBy(KEYWORD_1, MEMBER_ID)).isEqualTo(Collections.singletonList(articleResponse));
    }

    @Test
    void myPage() {
        given(memberService.findByNickName(NICKNAME)).willReturn(member);
        ReflectionTestUtils.setField(member, "id", MEMBER_ID);

        long articleNumber = 1L;
        long followerNumber = 1L;
        long followeeNumber = 1L;
        given(articleRepository.countArticleByAuthorId(MEMBER_ID)).willReturn(articleNumber);
        given(followService.countFollowers(MEMBER_ID)).willReturn(followerNumber);
        given(followService.countFollowees(MEMBER_ID)).willReturn(followeeNumber);

        MemberMyPageResponse memberMyPageResponse = Deencapsulation.invoke(MemberAssembler.class, "toMyPageResponse"
                , member, articleNumber, followerNumber, followeeNumber);
        assertThat(articleService.myPage(NICKNAME)).isEqualTo(memberMyPageResponse);
    }
}