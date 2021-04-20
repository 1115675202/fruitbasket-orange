package com.fruitbasket.orange.module.rbac.repository;

import com.fruitbasket.orange.module.rbac.pojo.entity.RbacUserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户账户
 *
 * @author LiuBing
 * @date 2020/12/15
 */
public interface UserAccountRep extends JpaRepository<RbacUserAccount, Integer> {

    /**
     * @param identifier 账号
     * @return 账户信息
     */
    RbacUserAccount getUserAccountByIdentifier(String identifier);
}
