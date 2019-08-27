package com.woowacourse.zzazanstagram.model.article.service;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleRequest;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleResponse;
import com.woowacourse.zzazanstagram.model.article.exception.ArticleException;
import com.woowacourse.zzazanstagram.model.article.repository.ArticleRepository;
import com.woowacourse.zzazanstagram.model.hashtag.service.HashTagService;
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

@Service
public class ArticleService {
    private static final Logger log = LoggerFactory.getLogger(ArticleService.class);
    private static final String TAG = "[ArticleService]";
    private static final int DEFAULT_PAGE_NUM = 0;

    private final ArticleRepository articleRepository;
    private final HashTagService hashTagService;
    private final MemberService memberService;
    private final S3Uploader s3Uploader;
    private final String dirName;

    // TODO 파라미터가 너무 많은 것 같지 않니?
    public ArticleService(ArticleRepository articleRepository, HashTagService hashTagService, MemberService memberService,
                          S3Uploader s3Uploader, @Value("${cloud.aws.s3.dirName.article}") String dirName) {
        this.articleRepository = articleRepository;
        this.hashTagService = hashTagService;
        this.memberService = memberService;
        this.s3Uploader = s3Uploader;
        this.dirName = dirName;
    }

//    public List<ArticleResponse> getArticleResponses() {
//        List<Article> articles = articleRepository.findAllByOrderByIdDesc();
//        return articles.stream()
//                .map(ArticleAssembler::toDto)
//                .collect(Collectors.toList());
//    }

    public void save(ArticleRequest dto, String email) {
        Member author = memberService.findByEmail(email);
        MultipartFile file = dto.getFile();
        String imageUrl = s3Uploader.upload(file, dirName);
        Article article = ArticleAssembler.toEntity(dto, imageUrl, author);
        articleRepository.save(article);
        hashTagService.save(article); // TODO 게시글은 저장됐는데 해시태그를 저장하다가 뻑이 난다면?

        log.info("{} imageUrl : {}", TAG, imageUrl);
        log.info("{} create() >> {}", TAG, article);
    }

    public ArticleResponse getArticle(Long articleId, String memberEmail) {
        Member loginMember = memberService.findByEmail(memberEmail);
        Article article = findArticleById(articleId);

        return ArticleAssembler.toDto(article, loginMember);
    }

    public Article findArticleById(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(() -> new ArticleException("해당 게시글을 찾을 수 없습니다."));
    }

    public Page<Article> getArticlePages(Long lastArticleId, List<Member> followers, int size) {
        PageRequest pageRequest = PageRequest.of(DEFAULT_PAGE_NUM, size);

        return articleRepository.findByIdLessThanAndAuthorInOrderByIdDesc(lastArticleId, followers, pageRequest);
    }

    public void delete(Long articleId, String email) {
        Article article = findArticleById(articleId);
        Member member = memberService.findByEmail(email);

        article.checkAuthentication(member);
        articleRepository.delete(article);
    }
}
