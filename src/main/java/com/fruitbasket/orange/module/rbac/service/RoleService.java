package com.fruitbasket.orange.module.rbac.service;

import cn.hutool.core.bean.BeanUtil;
import com.fruitbasket.orange.exception.BusinessException;
import com.fruitbasket.orange.module.common.vo.PageVO;
import com.fruitbasket.orange.module.rbac.pojo.entity.RbacRole;
import com.fruitbasket.orange.module.rbac.pojo.entity.RbacUser;
import com.fruitbasket.orange.module.rbac.pojo.query.RoleAddQuery;
import com.fruitbasket.orange.module.rbac.pojo.query.RoleUpdateQuery;
import com.fruitbasket.orange.module.rbac.pojo.query.RolePageableQuery;
import com.fruitbasket.orange.module.rbac.pojo.vo.RolePageVO;
import com.fruitbasket.orange.module.rbac.repository.RoleRep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.fruitbasket.orange.module.rbac.pojo.entity.RbacRole.DEFAULT_SORT_VALUE;
import static com.fruitbasket.orange.util.CustomBeanUtils.IGNORE_NULL_COPY_OPTION;
import static org.springframework.util.StringUtils.hasText;

/**
 * 权限
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@Slf4j
@Service
public class RoleService {

    private final RoleRep roleRep;

    public RoleService(RoleRep roleRep) {
        this.roleRep = roleRep;
    }

    /**
     * @Cacheable的几个属性详解：
     *     cacheNames/value：指定缓存组件的名字
     *     key：缓存数据使用的key,可以用它来指定。默认使用方法参数的值，一般不需要指定
     *     keyGenerator：作用和key一样，二选一
     *     cacheManager和cacheResolver作用相同：指定缓存管理器，二选一
     *     condition：指定符合条件才缓存，比如：condition="#id>3"
     *     也就是说传入的参数id>3才缓存数据
     *     unless：否定缓存，当unless为true时不缓存，可以获取方法结果进行判断
     *     sync：是否使用异步模式
     */
    /**
     * @param userId 用户ID
     * @return 用户所有角色
     */
    @Cacheable(cacheNames = "role")
    public List<RbacRole> listRolesOf(Integer userId) {
        RbacUser user = new RbacUser();
        user.setId(userId);
        return roleRep.findAllByUsersIsOrderBySortValueDesc(user);
    }

    /**
     * @param requestUrl 请求地址
     * @return 能访问该地址的角色
     */
    public Set<String> listBeAuthorizedRoleNamesOf(String requestUrl) {
        return new HashSet<String>() {{
            add("ADMIN");
        }};
    }

    /**
     * 分页查询权限
     *
     * @param query 分页以及检索参数
     * @return 分页信息
     */
    public PageVO<RolePageVO> listPageRoles(RolePageableQuery query) {
        Pageable pageable = PageRequest.of(query.getPageNumber(), query.getPageSize());
        Page<RbacRole> page = roleRep.findAllByRoleNameContainingOrRoleShowNameContainingOrderBySortValueDesc(
                query.getRoleName(), query.getRoleShowName(), pageable);
        return PageVO.of(page, RolePageVO::of);
    }

    /**
     * 新增一个角色
     *
     * @param query -
     * @return 完整角色信息
     */
    @Transactional
    public RolePageVO save(RoleAddQuery query) {
        RbacRole role = new RbacRole().setRoleName(query.getRoleName());
        if (roleRep.count(Example.of(role)) > 0) throw new BusinessException("角色名称[roleName]已存在");
        BeanUtil.copyProperties(query, role, IGNORE_NULL_COPY_OPTION);
        roleRep.save(role.setSortValue(DEFAULT_SORT_VALUE));
        return RolePageVO.of(role);
    }

    /**
     * 根据 ID 删除
     *
     * @param ids -
     * @return 删除数量
     */
    @Transactional
    public long deleteRoleIdIn(Set<Integer> ids) {
        return roleRep.deleteByIdIn(ids);
    }

    /**
     * 修改角色信息
     *
     * @param query -
     * @return 修改后的角色信息
     */
    @Transactional
    public RolePageVO updateRole(RoleUpdateQuery query) {
        Optional<RbacRole> roleOptional = roleRep.findById(query.getId());
        if (!roleOptional.isPresent()) throw new BusinessException("角色信息不存在");

        if (hasText(query.getRoleName())
                && roleRep.count(Example.of(new RbacRole().setRoleName(query.getRoleName()))) > 0) {
            throw new BusinessException("角色名称[roleName]已存在");
        }

        RbacRole role = roleOptional.get();
        BeanUtil.copyProperties(query, role, IGNORE_NULL_COPY_OPTION);
        roleRep.save(role);
        return RolePageVO.of(role);
    }
}
