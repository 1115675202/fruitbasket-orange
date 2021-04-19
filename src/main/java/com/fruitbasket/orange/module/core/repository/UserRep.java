package com.fruitbasket.orange.module.core.repository;

import com.fruitbasket.orange.module.core.pojo.entity.User;
import com.fruitbasket.orange.module.core.pojo.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/15
 */
public interface UserRep extends JpaRepository<User, Integer> {

    /**
     * 根据账户信息查找用户
     *
     * @param userAccount 用户账户信息
     * @return 用户信息
     */
    User getUserByUserAccountsIs(UserAccount userAccount);
}
