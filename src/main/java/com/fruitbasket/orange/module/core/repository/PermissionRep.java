package com.fruitbasket.orange.module.core.repository;

import com.fruitbasket.orange.dict.PermissionType;
import com.fruitbasket.orange.module.core.pojo.entity.Permission;
import com.fruitbasket.orange.module.core.pojo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 权限
 *
 * @author LiuBing
 * @date 2020/12/15
 */
public interface PermissionRep extends JpaRepository<Permission, Integer> {

    /**
     * 根据角色列表查询权限
     *
     * @param roles 角色列表
     * @return 权限列表
     */
    List<Permission> queryAllByRolesInOrderBySortValueDesc(List<Role> roles);


    List<Permission> queryAllByRolesInAndPermissionTypeIn(List<Role> roles, List<PermissionType> types);
}
