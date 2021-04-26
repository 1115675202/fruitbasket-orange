package cn.fruitbasket.orange.module.rbac.repository;

import cn.fruitbasket.orange.module.rbac.pojo.entity.RbacUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/15
 */
public interface UserRep extends JpaRepository<RbacUser, Integer> {

    /**
     * 根据用户名信息查找用户
     *
     * @param username -
     * @return 用户信息
     */
    RbacUser getRbacUserByUsernameIs(String username);

    /**
     * 根据 ID 删除
     *
     * @param ids ID 列表
     * @return 删除数量
     */
    long deleteByIdIn(Set<Integer> ids);

    /**
     * 按用户名统计行数，实现用户名验重
     *
     * @param username -
     * @return 行数
     */
    long countByUsernameIs(String username);

    /**
     * 按条件分页查询
     *
     * @param realName 真实姓名
     * @param pageable 分页参数
     * @return 用户列表
     */
    Page<RbacUser> findAllByRealNameContains(String realName, Pageable pageable);
}
