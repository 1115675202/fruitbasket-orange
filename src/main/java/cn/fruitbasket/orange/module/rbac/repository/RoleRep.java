package cn.fruitbasket.orange.module.rbac.repository;

import cn.fruitbasket.orange.module.rbac.pojo.entity.RbacRole;
import cn.fruitbasket.orange.module.rbac.pojo.entity.RbacUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

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

    /**
     * 分页查询角色
     * @param roleName 角色名称
     * @param roleShowName 角色显示名称
     * @param pageable 分页参数
     * @return 角色分页
     */
    Page<RbacRole> findAllByRoleNameContainingOrRoleShowNameContainingOrderBySortValueDesc
    (String roleName, String roleShowName, Pageable pageable);

    /**
     * 根据 ID 删除
     *
     * @param ids ID 列表
     * @return 删除数量
     */
    long deleteByIdIn(Set<Integer> ids);
}
