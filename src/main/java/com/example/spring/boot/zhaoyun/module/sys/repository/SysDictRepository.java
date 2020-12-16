package com.example.spring.boot.zhaoyun.module.sys.repository;

import com.example.spring.boot.zhaoyun.module.sys.pojo.entity.SysDict;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/15
 */
public interface SysDictRepository extends JpaRepository<SysDict, Integer> {
}
