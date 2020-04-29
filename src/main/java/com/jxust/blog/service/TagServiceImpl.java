package com.jxust.blog.service;

import com.jxust.blog.NotFindException;
import com.jxust.blog.dao.TagRepository;
import com.jxust.blog.po.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * @author sxtstart
 * @create 2020-02-10 20:41
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        Tag tag = new Tag();
        tag.setId(id);
        Example<Tag> example = Example.of(tag);
        Optional<Tag> optional = tagRepository.findOne(example);
      // optional.isPresent()是为了判断对象是否存在，如果存在就通过get()方法来获取对象
        if (optional.isPresent()) {
            tag = optional.get();
            return tag;
        } else {
            throw new NotFindException("该标签丢失了。。。");
        }
       // return tagRepository.findById(id).get();
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {

        return tagRepository.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {

        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) { //1,2,3

        return tagRepository.findAllById(convertToList(ids));
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size,sort);
        return tagRepository.findTop(pageable);
    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i = 0; i < idarray.length; i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagRepository.findById(id).get();
        if (t == null) {
            throw new NotFindException("不存在该类型");
        }
        BeanUtils.copyProperties(tag,t);
        return tagRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
