package com.jxust.blog.service;

import com.jxust.blog.po.User;

/**
 * @author sxtstart
 * @create 2020-02-10 18:57
 */

public interface UserService {

    User checkUser(String username, String password);

    User saveUser(User user);

}
