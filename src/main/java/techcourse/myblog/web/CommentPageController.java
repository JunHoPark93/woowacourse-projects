package techcourse.myblog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import techcourse.myblog.service.CommentService;
import techcourse.myblog.service.dto.UserSession;
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
    public String editCommentPage(@PathVariable("commentId") Long commentId, Model model, UserSession userSession) {
        model.addAttribute("comment", commentService.find(userSession, commentId));
        return "mycomment-edit";
    }

    @PutMapping("/{commentId}")
    public String editComment(@Valid CommentRequest commentRequest, @PathVariable("commentId") Long commentId, UserSession userSession) {
        commentService.update(commentRequest, userSession, commentId);
        return "redirect:/articles/" + commentRequest.getArticleId();
    }
}
