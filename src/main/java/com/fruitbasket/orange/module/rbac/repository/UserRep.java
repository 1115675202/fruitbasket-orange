package com.fruitbasket.orange.module.rbac.repository;

import com.fruitbasket.orange.module.rbac.pojo.entity.RbacUser;
import com.fruitbasket.orange.module.rbac.pojo.entity.RbacUserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/15
 */
public interface UserRep extends JpaRepository<RbacUser, Integer> {

    /**
     * 根据账户信息查找用户
     *
     * @param userAccount 用户账户信息
     * @return 用户信息
     */
    RbacUser getUserByUserAccountsIs(RbacUserAccount userAccount);
}
