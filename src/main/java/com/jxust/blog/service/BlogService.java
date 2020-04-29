package com.jxust.blog.service;

import com.jxust.blog.po.Blog;
import com.jxust.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author sxtstart
 * @create 2020-02-20 13:23
 */

public interface BlogService {

    Blog getBlog(Long id);

    Blog getAndConvert(Long id);

    Page<Blog> listBlog(Pageable pageable,BlogQuery blog);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(Pageable pageable, Long tagId);

    Page<Blog> listBlog(Pageable pageable, String query);

    Map<String, List<Blog>> archiveBlog();

    Long countBlog();

    List<Blog> listRecommendBlogTop(Integer size);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);
}
