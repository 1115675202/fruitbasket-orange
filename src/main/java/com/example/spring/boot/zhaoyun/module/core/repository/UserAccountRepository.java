package com.example.spring.boot.zhaoyun.module.core.repository;

import com.example.spring.boot.zhaoyun.module.core.pojo.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户账户
 *
 * @author LiuBing
 * @date 2020/12/15
 */
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
}
