package com.fruitbasket.orange.module.rbac.repository;

import com.fruitbasket.orange.module.rbac.pojo.entity.RbacRole;
import com.fruitbasket.orange.module.rbac.pojo.entity.RbacUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 角色
 *
 * @author LiuBing
 * @date 2020/12/15
 */
public interface RoleRep extends JpaRepository<RbacRole, Integer> {

    /**
     * 根据用户查询角色
     *
     * @param user 用户信息
     * @return 角色列表
     */
    List<RbacRole> findAllByUsersIsOrderBySortValueDesc(RbacUser user);
}
