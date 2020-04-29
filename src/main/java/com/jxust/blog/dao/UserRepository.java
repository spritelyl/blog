package com.jxust.blog.dao;

import com.jxust.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sxtstart
 * @create 2020-02-10 19:00
 */

public interface UserRepository extends JpaRepository<User,Long>{

    User findByUsernameAndPassword(String username, String password);
}
