package com.fruitbasket.orange.module.core.repository;

import com.fruitbasket.orange.module.core.pojo.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户账户
 *
 * @author LiuBing
 * @date 2020/12/15
 */
public interface UserAccountRep extends JpaRepository<UserAccount, Integer> {
}
