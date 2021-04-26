package cn.fruitbasket.orange.module.rbac.repository;

import cn.fruitbasket.orange.dict.PermissionType;
import cn.fruitbasket.orange.module.rbac.pojo.entity.RbacPermission;
import cn.fruitbasket.orange.module.rbac.pojo.entity.RbacRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

/**
 * 权限
 *
 * @author LiuBing
 * @date 2020/12/15
 */
public interface PermissionRep extends JpaRepository<RbacPermission, Integer> {

    /**
     * 根据角色列表查询权限
     *
     * @param roles 角色列表
     * @return 权限列表
     */
    List<RbacPermission> findAllByRolesInOrderBySortValueDesc(List<RbacRole> roles);


    List<RbacPermission> findAllByRolesInAndPermissionTypeIn(List<RbacRole> roles, List<PermissionType> types);

    /**
     * 根据 ID 删除
     *
     * @param ids ID 列表
     * @return 删除数量
     */
    long deleteByIdIn(Set<Integer> ids);

    /**
     * 按权限名称统计行数，实现权限名称验重
     *
     * @param permissionName -
     * @return 行数
     */
    long countByPermissionName(String permissionName);
}
