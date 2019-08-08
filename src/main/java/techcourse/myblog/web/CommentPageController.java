package techcourse.myblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import techcourse.myblog.domain.User;
import techcourse.myblog.service.CommentService;
import techcourse.myblog.service.dto.request.CommentRequest;

import javax.validation.Valid;

@Controller
@RequestMapping("/comment")
public class CommentPageController {
    private CommentService commentService;

    public CommentPageController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{commentId}")
    public String editCommentPage(@PathVariable("commentId") Long commentId, Model model, User user) {
        model.addAttribute("comment", commentService.find(user, commentId));
        return "mycomment-edit";
    }

    @PutMapping("/{commentId}")
    public String editComment(@Valid CommentRequest commentRequest, @PathVariable("commentId") Long commentId, User user) {
        commentService.update(commentRequest, user, commentId);
        return "redirect:/articles/" + commentRequest.getArticleId();
    }
}
