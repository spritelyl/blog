package com.jxust.blog.service;

import com.jxust.blog.po.Comment;

import java.util.List;

/**
 * @author sxtstart
 * @create 2020-03-20 20:14
 */

public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);

    void deleteComment(Long id);

    Comment getComment(Long id);
}
