package cn.fruitbasket.orange.module.rbac.service;

import cn.fruitbasket.orange.config.exception.ShowToClientException;
import cn.fruitbasket.orange.module.common.vo.PageVO;
import cn.fruitbasket.orange.module.rbac.pojo.entity.RbacPermission;
import cn.fruitbasket.orange.module.rbac.pojo.entity.RbacRole;
import cn.fruitbasket.orange.module.rbac.pojo.entity.RbacUser;
import cn.fruitbasket.orange.module.rbac.pojo.query.RoleAddQuery;
import cn.fruitbasket.orange.module.rbac.pojo.query.RoleBindPermissionsQuery;
import cn.fruitbasket.orange.module.rbac.pojo.query.RolePageableQuery;
import cn.fruitbasket.orange.module.rbac.pojo.query.RoleUpdateQuery;
import cn.fruitbasket.orange.module.rbac.pojo.vo.RoleDetailVO;
import cn.fruitbasket.orange.module.rbac.pojo.vo.RoleVO;
import cn.fruitbasket.orange.module.rbac.repository.RoleRep;
import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static cn.fruitbasket.orange.module.rbac.pojo.entity.RbacRole.DEFAULT_SORT_VALUE;
import static cn.fruitbasket.orange.util.CustomBeanUtils.IGNORE_NULL_COPY_OPTION;
import static java.util.stream.Collectors.toList;
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

    private static final String ROLE_NOT_FOUND = "角色信息不存在";

    private final RoleRep roleRep;

    private final PermissionService permissionService;

    public RoleService(RoleRep roleRep, @Lazy PermissionService permissionService) {
        this.roleRep = roleRep;
        this.permissionService = permissionService;
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
    public List<RbacRole> listRolesOf(Integer userId) {
        RbacUser user = new RbacUser().setId(userId);
        return roleRep.findAllByUsersIsOrderBySortValueDesc(user);
    }

    /**
     * 这个方法是给 CustomRoleSecurityFilter 调用的，因为它不是走的 controller 接口，所以 open-in-view = true 在这里无效
     * 无法保持与数据库的 session 链接，方法中获取权限信息的那一段会报错 no session。所以通过加 @Transactional 保持 session
     *
     * @param requestUrl 请求地址
     * @return 能访问该地址的角色
     */
    @Transactional
    public List<String> listBeAuthorizedRoleNamesOf(String requestUrl) {
        List<RbacPermission> permissions = permissionService.listPermissionsBy(requestUrl);
        return permissions.stream().flatMap(p -> p.getRoles().stream()).map(RbacRole::getRoleName).collect(toList());
    }

    /**
     * 分页查询
     *
     * @param query 分页以及检索参数
     * @return 分页信息
     */
    public PageVO<RoleVO> listPageRoles(RolePageableQuery query) {
        Pageable pageable = PageRequest.of(query.getPageNumber(), query.getPageSize());
        Page<RbacRole> page = roleRep.findAllByRoleNameContainingOrRoleShowNameContainingOrderBySortValueDesc(
                query.getRoleName(), query.getRoleShowName(), pageable);
        return PageVO.of(page, RoleVO::of);
    }

    /**
     * 新增一个角色
     *
     * @param query -
     * @return 完整角色信息
     */
    @Transactional
    public RoleVO save(RoleAddQuery query) {
        RbacRole role = new RbacRole().setRoleName(query.getRoleName());
        if (roleRep.count(Example.of(role)) > 0) throw new ShowToClientException("角色名称[roleName]已存在");
        BeanUtil.copyProperties(query, role, IGNORE_NULL_COPY_OPTION);
        roleRep.save(role.setSortValue(DEFAULT_SORT_VALUE));
        return RoleVO.of(role);
    }

    /**
     * 根据 ID 删除
     *
     * @param roleIds -
     * @return 删除数量
     */
    @Transactional
    public long deleteRolesIdIn(Set<Integer> roleIds) {
        return roleRep.deleteByIdIn(roleIds);
    }

    /**
     * 修改角色信息
     *
     * @param query -
     * @return 修改后的角色信息
     */
    @Transactional
    public RoleVO updateRole(RoleUpdateQuery query) {
        Optional<RbacRole> roleOptional = roleRep.findById(query.getId());
        if (!roleOptional.isPresent()) throw new ShowToClientException(ROLE_NOT_FOUND);

        if (hasText(query.getRoleName())
                && roleRep.count(Example.of(new RbacRole().setRoleName(query.getRoleName()))) > 0) {
            throw new ShowToClientException("角色名称[roleName]已存在");
        }

        RbacRole role = roleOptional.get();
        BeanUtil.copyProperties(query, role, IGNORE_NULL_COPY_OPTION);
        roleRep.save(role);
        return RoleVO.of(role);
    }

    /**
     * 根据角色ID查询角色
     *
     * @param roleIds 角色ID
     * @return 角色列表
     */
    public List<RbacRole> listRolesOf(Set<Integer> roleIds) {
        return roleRep.findAllById(roleIds);
    }

    /**
     * 角色绑定权限
     *
     * @param query 绑定信息
     */
    @Transactional
    public void bindingPermissions(RoleBindPermissionsQuery query) {
        RbacRole role = roleRep.findById(query.getRoleId())
                .orElseThrow(() -> new ShowToClientException(ROLE_NOT_FOUND));

        List<RbacPermission> permissions =
                new ArrayList<>(permissionService.listPermissionsByUserId(query.getPermissionIds()));
        if (permissions.size() < query.getPermissionIds().size()) {
            permissions.forEach(permission ->
                    query.getPermissionIds().remove(permission.getId()));
            throw new ShowToClientException("权限信息不存在：permissionIds" + query.getPermissionIds());
        }

        role.setPermissions(permissions);
        roleRep.save(role);
    }

    /**
     * 获取角色详情信息
     *
     * @param roleId 角色ID
     * @return -
     */
    public RoleDetailVO getRoleDetail(Integer roleId) {
        RbacRole role = roleRep.findById(roleId)
                .orElseThrow(() -> new ShowToClientException(ROLE_NOT_FOUND));

        List<RbacPermission> allPermissions = permissionService.listAllPermissions();
        return RoleDetailVO.of(role, allPermissions);
    }
}
