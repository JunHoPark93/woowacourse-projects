package com.woowacourse.zzazanstagram.model.comment.service;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.article.service.ArticleService;
import com.woowacourse.zzazanstagram.model.comment.domain.Comment;
import com.woowacourse.zzazanstagram.model.comment.domain.vo.CommentContents;
import com.woowacourse.zzazanstagram.model.comment.dto.CommentResponse;
import com.woowacourse.zzazanstagram.model.comment.repository.CommentRepository;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.service.MemberService;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final MemberService memberService;
    private final ArticleService articleService;
    private final CommentRepository commentRepository;

    public CommentService(MemberService memberService, ArticleService articleService, CommentRepository commentRepository) {
        this.memberService = memberService;
        this.articleService = articleService;
        this.commentRepository = commentRepository;
    }

    // TODO 파라미터 순서 - 원시형 먼저
    public CommentResponse save(CommentContents commentContents, long articleId, String email) {
        Member member = memberService.findByEmail(email);
        Article article = articleService.findById(articleId);

        // TODO 한줄로
        Comment comment = new Comment(commentContents, article, member);
        Comment saveComment = commentRepository.save(comment);

        return CommentAssembler.toDto(saveComment);
    }
}