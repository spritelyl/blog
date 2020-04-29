package com.jxust.blog.service;

import com.jxust.blog.NotFindException;
import com.jxust.blog.dao.UserRepository;
import com.jxust.blog.po.User;
import com.jxust.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author sxtstart
 * @create 2020-02-10 18:58
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setType(0);
        user.setPassword(MD5Utils.code(user.getPassword()));
        return userRepository.save(user);
    }
}
