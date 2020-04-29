package com.jxust.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author sxtstart
 * @create 2020-03-21 19:29
 */
@Controller
public class AboutShowController {

    @GetMapping("/about")
    public String about() {

        return "about";
    }
}
