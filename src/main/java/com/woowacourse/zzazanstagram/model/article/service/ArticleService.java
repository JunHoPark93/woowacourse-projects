package com.woowacourse.zzazanstagram.model.article.service;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleMyPageResponse;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleRequest;
import com.woowacourse.zzazanstagram.model.article.dto.ArticleResponse;
import com.woowacourse.zzazanstagram.model.article.exception.ArticleException;
import com.woowacourse.zzazanstagram.model.article.repository.ArticleRepository;
import com.woowacourse.zzazanstagram.model.hashtag.domain.ArticleHashtag;
import com.woowacourse.zzazanstagram.model.hashtag.service.HashtagService;
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

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private static final Logger log = LoggerFactory.getLogger(ArticleService.class);
    private static final String TAG = "[ArticleService]";
    private static final int DEFAULT_PAGE_NUM = 0;

    private final ArticleRepository articleRepository;
    private final HashtagService hashtagService;
    private final MemberService memberService;
    private final S3Uploader s3Uploader;
    private final String dirName;

    public ArticleService(ArticleRepository articleRepository, HashtagService hashtagService, MemberService memberService,
                          S3Uploader s3Uploader, @Value("${cloud.aws.s3.dirName.article}") String dirName) {
        this.articleRepository = articleRepository;
        this.hashtagService = hashtagService;
        this.memberService = memberService;
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
        hashtagService.save(article);

        log.info("{} imageUrl : {}", TAG, imageUrl);
        log.info("{} create() >> {}", TAG, article);
    }

    // TODO find~로 리네임, response 타입 명시, + 전치사 (findArticleResponseBy)
    public ArticleResponse getArticle(Long articleId, String email) {
        Member loginMember = memberService.findByEmail(email);
        Article article = findArticleById(articleId);

        return ArticleAssembler.toDto(article, loginMember);
    }

    public Article findArticleById(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(() -> new ArticleException("해당 게시글을 찾을 수 없습니다."));
    }

    // TODO page 시에만 fetch naming
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

    public List<ArticleMyPageResponse> getMyPageArticles(Long lastArticleId, int size, long id) {
        PageRequest pageRequest = PageRequest.of(DEFAULT_PAGE_NUM, size);
        Page<Article> articles = articleRepository.findByIdLessThanAndAuthorIdEqualsOrderByIdDesc(lastArticleId, id, pageRequest);

        return articles.stream().map(ArticleAssembler::toMyPageDto).collect(Collectors.toList());
    }

    public long countByAuthorId(Long id) {
        return articleRepository.countArticleByAuthorId(id);
    }

    public List<ArticleResponse> findArticleResponsesBy(String keyword, Long memberId) {
        Member loginMember = memberService.findById(memberId);
        return hashtagService.findAllByHashtag(keyword)
                .stream()
                .map(ArticleHashtag::getArticle)
                .map(article -> ArticleAssembler.toDto(article, loginMember))
                .collect(Collectors.toList());
    }
}
