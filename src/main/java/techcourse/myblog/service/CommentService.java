package techcourse.myblog.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import techcourse.myblog.domain.Article;
import techcourse.myblog.domain.Comment;
import techcourse.myblog.domain.CommentRepository;
import techcourse.myblog.domain.User;
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
    private ModelMapper commentMapper;

    public CommentService(CommentRepository commentRepository, ModelMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public void save(CommentRequest commentRequest, Article article, User user) {
        Comment comment = new Comment(commentRequest.getContents(), article, user);
        commentRepository.save(comment);
    }

    public List<CommentResponse> findByArticle(Article article) {
        return commentRepository.findByArticle(article).stream()
                .map(comment -> commentMapper.map(comment, CommentResponse.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public Comment update(CommentRequest commentRequest, User user, Long commentId) {
        Comment comment = findComment(user, commentId);
        return comment.updateContents(commentRequest.getContents());
    }

    public void deleteById(Long commentId, User user) {
        checkAuthor(user, commentId);
        commentRepository.deleteById(commentId);
    }

    public Comment findComment(User user, Long commentId) {
        Comment comment = findById(commentId);
        if (comment.isCommentor(user)) {
            throw new InvalidAuthorException("작성자가 아닙니다");
        }
        return comment;
    }

    private Comment findById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new NoCommentException("댓글이 존재하지 않습니다"));
    }

    private void checkAuthor(User user, Long commentId) {
        Comment comment = findById(commentId);
        if (!comment.isCommentor(user)) {
            throw new InvalidAuthorException("작성자가 아닙니다");
        }
    }
}
