package com.jxust.blog.web.admin;

import com.jxust.blog.po.User;
import com.jxust.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author sxtstart
 * @create 2020-02-10 19:04
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage() {
        return "admin/login";
    }

    @Value("${user.avatar}")
    private String userAvatar;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes attributes) {
        User user = userService.checkUser(username, password);
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user",user);
            if (user.getType() == 1) {
                return "admin/index";
            } else {
                return "redirect:/";
            }

        } else {
            attributes.addFlashAttribute("message","用户名或密码错误");

            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }

    @GetMapping("/registerPage")
    public String registerPage() {
        return "admin/register";
    }

    @PostMapping("/register")
    public String register(User user, RedirectAttributes attributes) {
        user.setAvatar(userAvatar);
        userService.saveUser(user);
        attributes.addFlashAttribute("message","注册成功，快去登录吧！");
        return "admin/login";
    }
}
