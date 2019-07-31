package techcourse.myblog.service;

import org.springframework.stereotype.Service;
import techcourse.myblog.domain.Article;
import techcourse.myblog.domain.Comment;
import techcourse.myblog.domain.CommentRepository;
import techcourse.myblog.domain.User;
import techcourse.myblog.service.dto.CommentRequest;
import techcourse.myblog.service.dto.CommentResponse;
import techcourse.myblog.service.exception.InvalidAuthorException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void save(CommentRequest commentRequest, Article article, User user) {
        Comment comment = new Comment(commentRequest.getContents(), article, user);
        commentRepository.save(comment);
    }

    public List<CommentResponse> findByArticle(Article article) {
        return commentRepository.findByArticle(article).stream()
                .map(comment -> new CommentResponse(comment.getId(),
                        comment.getContents(),
                        comment.getCreatedDate().until(LocalDateTime.now(), ChronoUnit.MILLIS),
                        comment.getCommenter(),
                        comment.getArticle())).collect(Collectors.toList());
    }

    @Transactional
    public Comment update(CommentRequest commentRequest, User user, Long commentId) {
        Comment comment = findByCommenterAndId(user, commentId);
        return comment.updateContents(commentRequest.getContents());
    }

    public Comment findByCommenterAndId(User user, Long commentId) {
        return commentRepository.findByCommenterAndId(user, commentId)
                .orElseThrow(() -> new InvalidAuthorException("작성자가 아닙니다"));
    }

    public void deleteById(Long commentId, User user) {
        findByCommenterAndId(user, commentId);
        commentRepository.deleteById(commentId);
    }
}
