package com.jxust.blog.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sxtstart
 * @create 2020-02-21 20:31
 */

public class MyBeanUtils {

    /**
     * 获取所有的属性值为空属性名数组
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
        List<String> nullPropertyNames = new ArrayList<>();
        for (PropertyDescriptor pd : pds) {
            String pdName = pd.getName();
            if (beanWrapper.getPropertyValue(pdName) == null) {
                nullPropertyNames.add(pdName);
            }
        }
        return nullPropertyNames.toArray(new String[nullPropertyNames.size()]);
    }
}
