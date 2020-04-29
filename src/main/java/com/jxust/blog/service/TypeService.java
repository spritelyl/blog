package com.jxust.blog.service;

import com.jxust.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author sxtstart
 * @create 2020-02-10 20:39
 */

public interface TypeService {

    Type saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);

    Type updateType(Long id, Type type);

    void deleteType(Long id);
}
