package techcourse.myblog.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import techcourse.myblog.domain.Article;
import techcourse.myblog.domain.Comment;
import techcourse.myblog.domain.CommentRepository;
import techcourse.myblog.domain.User;
import techcourse.myblog.service.dto.request.CommentRequest;
import techcourse.myblog.service.dto.response.CommentResponse;
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

    public List<CommentResponse> saveAndGet(CommentRequest commentRequest, User user) {
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

    public List<CommentResponse> find(Long articleId) {
        return commentRepository.findByArticleId(articleId).stream()
                .map(comment -> commentMapper.map(comment, CommentResponse.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(CommentRequest commentRequest, User user, Long commentId) {
        Comment comment = findComment(user, commentId);
        comment.updateContents(commentRequest.getContents());
    }

    public List<CommentResponse> deleteAndGet(Long commentId, User user) {
        Article article = findComment(user, commentId).getArticle();
        commentRepository.deleteById(commentId);
        return findByArticle(article);
    }

    private Comment findComment(User user, Long commentId) {
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

    public CommentResponse find(User user, Long commentId) {
        return commentMapper.map(findComment(user, commentId), CommentResponse.class);
    }
}
