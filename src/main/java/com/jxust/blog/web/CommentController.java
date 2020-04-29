package com.jxust.blog.web;

import com.jxust.blog.po.Comment;
import com.jxust.blog.po.User;
import com.jxust.blog.service.BlogService;
import com.jxust.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author sxtstart
 * @create 2020-03-20 20:06
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;


    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model) {
        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        User user = (User) session.getAttribute("user");
        comment.setAvatar(user.getAvatar());
        if (user.getType() == 1) {
            comment.setAdminComment(true);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }

    @GetMapping("/comment/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        Comment comment = commentService.getComment(id);
        Long blogId = comment.getBlog().getId();
        commentService.deleteComment(id);
        attributes.addFlashAttribute("message","删除评论成功");
        return "redirect:/blog/" + blogId;
    }
}
