package com.fruitbasket.orange.module.core.repository;

import com.fruitbasket.orange.module.core.pojo.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 菜单
 *
 * @author LiuBing
 * @date 2020/12/15
 */
public interface UserMenuRepository extends JpaRepository<Menu, Integer> {
}
