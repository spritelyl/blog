package com.jxust.blog.web.admin;

import com.jxust.blog.po.Blog;
import com.jxust.blog.po.Tag;
import com.jxust.blog.po.User;
import com.jxust.blog.service.BlogService;
import com.jxust.blog.service.TagService;
import com.jxust.blog.service.TypeService;
import com.jxust.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author sxtstart
 * @create 2020-02-10 19:55
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                Pageable pageable, BlogQuery blog, Model model) {

        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return LIST;
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 10, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                 Pageable pageable, BlogQuery blog, Model model) {

        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model) {
        setTypeAndTag(model);
        model.addAttribute("blog", new Blog());
        return INPUT;
    }

    private void setTypeAndTag(Model model) {
        model.addAttribute("tags",tagService.listTag());
        model.addAttribute("types", typeService.listType());
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(Model model, @PathVariable Long id) {
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        return INPUT;
    }

    @PostMapping("/blogs")
    public String post(Blog blog, HttpSession session, RedirectAttributes attributes) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));

        Blog b;
        if (blog.getId() == null) {
            b = blogService.saveBlog(blog);
        } else {
            b = blogService.updateBlog(blog.getId(),blog);
        }
        List<Tag> tags = blog.getTags();
        if (b == null) {
            attributes.addFlashAttribute("message","操作失败");
        } else {
            attributes.addFlashAttribute("message","操作成功");
        }
        return REDIRECT_LIST;
    }

    @GetMapping("/blogs/{id}/delete")
    public String delte(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return REDIRECT_LIST;
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        model.addAttribute("newblogs",blogService.listRecommendBlogTop(3));
        return "admin/_fragments :: newblogList";
    }
}
