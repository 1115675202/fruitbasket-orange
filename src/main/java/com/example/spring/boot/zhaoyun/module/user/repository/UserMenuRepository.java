package com.example.spring.boot.zhaoyun.module.user.repository;

import com.example.spring.boot.zhaoyun.module.user.pojo.entity.UserMenu;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/15
 */
public interface UserMenuRepository extends JpaRepository<UserMenu, Integer> {
}
