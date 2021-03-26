package com.fruitbasket.orange.module.sys.repository;

import com.fruitbasket.orange.module.sys.pojo.entity.SysDict;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 字典
 *
 * @author LiuBing
 * @date 2020/12/15
 */
public interface SysDictRepository extends JpaRepository<SysDict, Integer> {
}
