package com.fruitbasket.orange.module.core.repository;

import com.fruitbasket.orange.module.core.pojo.entity.Role;
import com.fruitbasket.orange.module.core.pojo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 角色
 *
 * @author LiuBing
 * @date 2020/12/15
 */
public interface RoleRep extends JpaRepository<Role, Integer> {

    /**
     * 根据用户查询角色
     *
     * @param user 用户信息
     * @return 角色列表
     */
    List<Role> queryAllByUsersIsOrderBySortValueDesc(User user);
}
