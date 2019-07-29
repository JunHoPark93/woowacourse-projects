package techcourse.myblog.service;

import org.springframework.stereotype.Service;
import techcourse.myblog.domain.Article;
import techcourse.myblog.domain.Comment;
import techcourse.myblog.domain.CommentRepository;
import techcourse.myblog.domain.User;
import techcourse.myblog.service.dto.CommentRequest;

import java.util.List;

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

    public List<Comment> findByArticle(Article article) {
        return commentRepository.findByArticle(article);
    }
}
