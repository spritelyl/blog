package com.jxust.blog.service;

import com.jxust.blog.NotFindException;
import com.jxust.blog.dao.TypeRepository;
import com.jxust.blog.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * @author sxtstart
 * @create 2020-02-10 20:41
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        Type type = new Type();
        type.setId(id);
        Example<Type> example = Example.of(type);
        Optional<Type> optional = typeRepository.findOne(example);
        //optional.isPresent()是为了判断对象是否存在，如果存在就通过get()方法来获取对象
        if (optional.isPresent()) {
            type = optional.get();
            return type;
        } else {
            throw new NotFindException("该分类丢失了。。。");
        }
        /*return typeRepository.findById(id).get();*/
    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {

        return typeRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return typeRepository.findTop(pageable);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.findById(id).get();
        if (t == null) {
            throw new NotFindException("不存在该类型");
        }
        BeanUtils.copyProperties(type,t);
        return typeRepository.save(t);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}
