package com.fruitbasket.orange.module.core.repository;

import com.fruitbasket.orange.module.core.pojo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/15
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
