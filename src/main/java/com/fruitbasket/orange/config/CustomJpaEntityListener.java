package com.fruitbasket.orange.config;

import cn.hutool.core.util.ReflectUtil;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import static java.lang.Boolean.FALSE;

/**
 * 监听 jpa 实体事件，在持久化前后加入逻辑
 * 如果填充字段被分装在一个父类中： Class<?> aClass = object.getClass().getSuperclass();
 * @author LiuBing
 * @date 2021/4/22
 */
@Component
public class CustomJpaEntityListener {

    /**
     * 新增数据前
     */
    @PrePersist
    public void prePersist(Object object) throws IllegalArgumentException {
        ReflectUtil.setFieldValue(object, "deleted", FALSE);
    }

    /**
     * 更新数据前
     */
    @PreUpdate
    public void preUpdate(Object object) throws IllegalArgumentException {
    }


    /**
     * 新增数据后
     */
    @PostPersist
    public void postPersist(Object object) throws IllegalArgumentException {

    }

    /**
     * 更新数据后
     */
    @PostUpdate
    public void postUpdate(Object object) throws IllegalArgumentException {
    }
}

