package techcourse.myblog.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import techcourse.myblog.domain.*;
import techcourse.myblog.service.dto.UserSession;
import techcourse.myblog.service.dto.request.CommentRequest;
import techcourse.myblog.service.dto.response.CommentResponse;
import techcourse.myblog.service.exception.InvalidAuthorException;
import techcourse.myblog.service.exception.NoCommentException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private ArticleService articleService;
    private UserService userService;
    private CommentRepository commentRepository;
    private ModelMapper commentMapper;

    public CommentService(ArticleService articleService, UserService userService, CommentRepository commentRepository, ModelMapper commentMapper) {
        this.articleService = articleService;
        this.userService = userService;
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public List<CommentResponse> saveAndGet(CommentRequest commentRequest, UserSession userSession) {
        Article article = articleService.findById(commentRequest.getArticleId());
        Comment comment = new Comment(commentRequest.getContents(), article, findUser(userSession));
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
    public void update(CommentRequest commentRequest, UserSession userSession, Long commentId) {
        Comment comment = findComment(findUser(userSession), commentId);
        comment.updateContents(commentRequest.getContents());
    }

    public List<CommentResponse> deleteAndGet(Long commentId, UserSession userSession) {
        Article article = findComment(findUser(userSession), commentId).getArticle();
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

    public CommentResponse find(UserSession userSession, Long commentId) {
        User user = findUser(userSession);
        return commentMapper.map(findComment(user, commentId), CommentResponse.class);
    }

    private User findUser(UserSession userSession) {
        return userService.findById(userSession.getId());
    }
}
