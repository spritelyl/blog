package com.jxust.blog.dao;

import com.jxust.blog.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author sxtstart
 * @create 2020-03-20 20:16
 */

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
