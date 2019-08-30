package com.woowacourse.zzazanstagram.model.comment.service;

import com.woowacourse.zzazanstagram.model.article.domain.Article;
import com.woowacourse.zzazanstagram.model.article.service.ArticleService;
import com.woowacourse.zzazanstagram.model.comment.domain.Comment;
import com.woowacourse.zzazanstagram.model.comment.dto.CommentResponse;
import com.woowacourse.zzazanstagram.model.comment.repository.CommentRepository;
import com.woowacourse.zzazanstagram.model.member.domain.Member;
import com.woowacourse.zzazanstagram.model.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static com.woowacourse.zzazanstagram.TestConstant.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class CommentServiceTest {

    @InjectMocks
    CommentService commentService;

    @Mock
    private MemberService memberService;

    @Mock
    private ArticleService articleService;

    @Mock
    private CommentRepository commentRepository;

    @Test
    void commentSaveTest() {
        long memberId = 1L;
        long articleId = 1L;
        long commentId = 1L;

        Member member = Member.MemberBuilder.aMember()
                .email(EMAIL_VALUE)
                .name(NAME_VALUE)
                .nickName(NICKNAME_VALUE)
                .password(PASSWORD_VALUE)
                .profile(PROFILE_IMAGE_VALUE)
                .build();

        ReflectionTestUtils.setField(member, "id", memberId);

        Article article = new Article(IMAGE, CONTENTS, member);
        ReflectionTestUtils.setField(article, "id", articleId);

        given(memberService.findByEmail(EMAIL_VALUE)).willReturn(member);
        given(articleService.findById(articleId)).willReturn(article);

        Comment comment = new Comment(COMMENT_CONTENTS, article, member);
        ReflectionTestUtils.setField(comment, "id", commentId);

        given(commentRepository.save(ArgumentMatchers.argThat(argument -> argument.getContentsValue().equals(COMMENT_CONTENTS_VALUE))))
                .willReturn(comment);

        CommentResponse commentResponse = commentService.save(COMMENT_CONTENTS, articleId, EMAIL_VALUE);

        CommentResponse expectResponse = new CommentResponse(COMMENT_CONTENTS_VALUE, commentId, member.getNickNameValue());
        assertThat(commentResponse).isEqualTo(expectResponse);
    }
}