package com.jxust.blog.service;

import com.jxust.blog.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author sxtstart
 * @create 2020-02-10 20:39
 */

public interface TagService {

    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTag();

    List<Tag> listTag(String ids);

    List<Tag> listTagTop(Integer size);

    Tag updateTag(Long id, Tag tag);

    void deleteTag(Long id);
}
