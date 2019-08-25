package com.woowacourse.zzazanstagram.model.article.service;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleRequest;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleResponse;
import com.woowacourse.zzazanstagram.model.article.exception.ArticleException;
import com.woowacourse.zzazanstagram.model.article.repository.ArticleRepository;
import com.woowacourse.zzazanstagram.model.follow.service.FollowService;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.service.MemberService;
import com.woowacourse.zzazanstagram.util.S3Uploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private static final Logger log = LoggerFactory.getLogger(ArticleService.class);
    private static final String TAG = "[ArticleService]";
    private static final int DEFAULT_PAGE_NUM = 0;

    private final ArticleRepository articleRepository;
    private final MemberService memberService;
    private final FollowService followService;
    private final S3Uploader s3Uploader;
    private final String dirName;

    public ArticleService(ArticleRepository articleRepository, MemberService memberService, FollowService followService, S3Uploader s3Uploader, @Value("${cloud.aws.s3.dirName.article}") String dirName) {
        this.articleRepository = articleRepository;
        this.memberService = memberService;
        this.followService = followService;
        this.s3Uploader = s3Uploader;
        this.dirName = dirName;
    }

    public List<ArticleResponse> getArticleResponses() {
        List<Article> articles = articleRepository.findAllByOrderByIdDesc();
        return articles.stream()
                .map(ArticleAssembler::toDto)
                .collect(Collectors.toList());
    }

    public void save(ArticleRequest dto, String email) {
        Member author = memberService.findByEmail(email);
        MultipartFile file = dto.getFile();
        String imageUrl = s3Uploader.upload(file, dirName);
        Article article = ArticleAssembler.toEntity(dto, imageUrl, author);
        articleRepository.save(article);

        log.info("{} imageUrl : {}", TAG, imageUrl);
        log.info("{} create() >> {}", TAG, article);
    }

    public Article findArticleById(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(() -> new ArticleException("해당 게시글을 찾을 수 없습니다."));
    }

    public List<ArticleResponse> getArticlePages(Long lastArticleId, int size, Long memberId) {
        List<Member> followers = findFollowersByMemberId(memberId);

        addLoginMemberTo(followers, memberId);

        PageRequest pageRequest = PageRequest.of(DEFAULT_PAGE_NUM, size);
        Page<Article> articles = articleRepository.findArticlesByPages(lastArticleId, followers, pageRequest);

        return articles.stream().map(ArticleAssembler::toDto).collect(Collectors.toList());
    }

    private List<Member> findFollowersByMemberId(Long memberId) {
        List<Long> followingsIds = followService.findFollowingsIds(memberId);
        return memberService.findByIds(followingsIds);
    }

    private void addLoginMemberTo(List<Member> followers, Long memberId) {
        followers.add(memberService.findById(memberId));
    }
}
