package techcourse.myblog.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import techcourse.myblog.domain.*;
import techcourse.myblog.service.dto.CommentRequest;
import techcourse.myblog.service.dto.CommentResponse;
import techcourse.myblog.service.exception.InvalidAuthorException;
import techcourse.myblog.service.exception.NoCommentException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private ArticleService articleService;
    private ModelMapper commentMapper;

    public CommentService(CommentRepository commentRepository, ArticleService articleService, ModelMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.articleService = articleService;
        this.commentMapper = commentMapper;
    }

    public List<CommentResponse> saveAndGetComments(CommentRequest commentRequest, User user) {
        Article article = articleService.findById(commentRequest.getArticleId());
        Comment comment = new Comment(commentRequest.getContents(), article, user);
        commentRepository.save(comment);

        return findByArticle(article);
    }

    private List<CommentResponse> findByArticle(Article article) {
        return commentRepository.findByArticle(article).stream()
                .map(comment -> commentMapper.map(comment, CommentResponse.class))
                .collect(Collectors.toList());
    }

    public List<CommentResponse> findByArticleId(Long articleId) {
        return commentRepository.findByArticleId(articleId).stream()
                .map(comment -> commentMapper.map(comment, CommentResponse.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public Comment update(CommentRequest commentRequest, User user, Long commentId) {
        Comment comment = findComment(user, commentId);
        return comment.updateContents(commentRequest.getContents());
    }

    public List<CommentResponse> deleteAndGetComments(Long commentId, User user) {
        Article article = findComment(user, commentId).getArticle();
        commentRepository.deleteById(commentId);
        return findByArticle(article);
    }

    public Comment findComment(User user, Long commentId) {
        Comment comment = findById(commentId);
        if (!comment.isCommentor(user)) {
            throw new InvalidAuthorException("작성자가 아닙니다");
        }
        return comment;
    }

    private Comment findById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new NoCommentException("댓글이 존재하지 않습니다"));
    }
}
