package com.woowacourse.zzazanstagram.model.article.service;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleMyPageResponse;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleRequest;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleResponse;
import com.woowacourse.zzazanstagram.model.article.exception.ArticleException;
import com.woowacourse.zzazanstagram.model.article.repository.ArticleRepository;
import com.woowacourse.zzazanstagram.model.follow.service.FollowService;
import com.woowacourse.zzazanstagram.model.hashtag.domain.HashTag;
import com.woowacourse.zzazanstagram.model.hashtag.service.HashTagService;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.dto.MemberResponse;
import com.woowacourse.zzazanstagram.model.member.service.MemberService;
import com.woowacourse.zzazanstagram.util.S3Uploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private static final Logger log = LoggerFactory.getLogger(ArticleService.class);
    private static final String TAG = "[ArticleService]";
    private static final int DEFAULT_PAGE_NUM = 0;

    private final ArticleRepository articleRepository;
    private final HashTagService hashTagService;
    private final MemberService memberService;
    private final FollowService followService;
    private final S3Uploader s3Uploader;
    private final String dirName;

    public ArticleService(ArticleRepository articleRepository, HashTagService hashTagService, MemberService memberService,
                          FollowService followService, S3Uploader s3Uploader, @Value("${cloud.aws.s3.dirName.article}") String dirName) {
        this.articleRepository = articleRepository;
        this.hashTagService = hashTagService;
        this.memberService = memberService;
        this.followService = followService;
        this.s3Uploader = s3Uploader;
        this.dirName = dirName;
    }

    @Transactional
    public void save(ArticleRequest dto, String email) {
        Member author = memberService.findByEmail(email);
        MultipartFile file = dto.getFile();
        String imageUrl = s3Uploader.upload(file, dirName);
        Article article = ArticleAssembler.toEntity(dto, imageUrl, author);
        articleRepository.save(article);
        hashTagService.save(article);

        log.info("{} imageUrl : {}", TAG, imageUrl);
        log.info("{} create() >> {}", TAG, article);
    }

    public ArticleResponse findArticleResponseBy(Long articleId, String email) {
        Member loginMember = memberService.findByEmail(email);
        Article article = findById(articleId);

        return ArticleAssembler.toDto(article, loginMember);
    }

    public Article findById(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(() -> new ArticleException("해당 게시글을 찾을 수 없습니다."));
    }

    public List<ArticleResponse> fetchArticlePages(Long lastArticleId, int size, Long loginMemberId) {
        List<Member> followers = findFollowersByMemberId(loginMemberId);
        addLoginMemberTo(followers, loginMemberId);

        PageRequest pageRequest = PageRequest.of(DEFAULT_PAGE_NUM, size);
        Page<Article> articles = articleRepository.findByIdLessThanAndAuthorInOrderByIdDesc(lastArticleId, followers, pageRequest);

        Member loginMember = memberService.findById(loginMemberId);
        return articles.stream().map(article -> ArticleAssembler.toDto(article, loginMember))
                .collect(Collectors.toList());
    }

    private List<Member> findFollowersByMemberId(Long memberId) {
        List<Long> followingsIds = followService.findFollowingsIds(memberId);
        return memberService.findAllByIds(followingsIds);
    }

    private void addLoginMemberTo(List<Member> followers, Long loginMemberId) {
        Member loginMember = memberService.findById(loginMemberId);
        followers.add(loginMember);
    }

    public void deleteById(Long articleId, String email) {
        Article article = findById(articleId);
        Member member = memberService.findByEmail(email);

        article.checkAuthentication(member);
        articleRepository.delete(article);
    }

    public List<ArticleMyPageResponse> findArticleMyPageResponsesBy(Long lastArticleId, int size, String nickName) {
        MemberResponse memberResponse = memberService.findByNickName(nickName);

        PageRequest pageRequest = PageRequest.of(DEFAULT_PAGE_NUM, size);
        Page<Article> articles = articleRepository.findByIdLessThanAndAuthorIdEqualsOrderByIdDesc(lastArticleId
                , memberResponse.getId(), pageRequest);

        return articles.stream().map(ArticleAssembler::toMyPageDto).collect(Collectors.toList());
    }

    public long countByAuthorId(Long id) {
        return articleRepository.countArticleByAuthorId(id);
    }

    public List<ArticleResponse> findArticleResponsesByTagKeyword(String tagKeyword, Long memberId) {
        Member loginMember = memberService.findById(memberId);
        return hashTagService.findAllByTagKeyword(tagKeyword)
                .stream()
                .map(HashTag::getArticle)
                .map(article -> ArticleAssembler.toDto(article, loginMember))
                .collect(Collectors.toList());
    }
}
