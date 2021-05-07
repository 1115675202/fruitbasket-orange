package cn.fruitbasket.orange.module.rbac.service;

import cn.fruitbasket.orange.config.exception.ShowToClientException;
import cn.fruitbasket.orange.module.rbac.pojo.entity.RbacPermission;
import cn.fruitbasket.orange.module.rbac.pojo.entity.RbacRole;
import cn.fruitbasket.orange.module.rbac.pojo.query.PermissionAddQuery;
import cn.fruitbasket.orange.module.rbac.pojo.query.PermissionUpdateQuery;
import cn.fruitbasket.orange.module.rbac.pojo.vo.PermissionVO;
import cn.fruitbasket.orange.module.rbac.repository.PermissionRep;
import cn.fruitbasket.orange.util.CustomBeanUtils;
import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

import static cn.fruitbasket.orange.dict.PermissionType.MENU;
import static cn.fruitbasket.orange.module.rbac.pojo.entity.RbacPermission.*;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

/**
 * 权限
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@Slf4j
@Service
public class PermissionService {

    private final PermissionRep permissionRep;

    private final RoleService roleService;

    /**
     * 列出用户拥有的权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public List<RbacPermission> listPermissionsByUserId(Integer userId) {
        List<RbacRole> roles = roleService.listRolesOf(userId);
        return CollectionUtils.isEmpty(roles) ? emptyList() :
                roles.stream().flatMap(role -> role.getPermissions().stream()).collect(toList());
    }

    /**
     * @return 树形权限数据
     */
    public List<PermissionVO> treeOfAllPermissions() {
        return PermissionVO.treeOf(listAllPermissions()).getChildren();
    }

    /**
     * @return 所有权限数据
     */
    public List<RbacPermission> listAllPermissions() {
        return permissionRep.findAll();
    }

    /**
     * 根据权限链接查询权限
     *
     * @param permissionLink -
     * @return -
     */
    public List<RbacPermission> listPermissionsBy(String permissionLink) {
        return permissionRep.findAll(Example.of(new RbacPermission().setPermissionLink(permissionLink)));
    }

    /**
     * 新增一个权限
     *
     * @param query -
     * @return 完整权限信息
     */
    @Transactional
    public PermissionVO save(PermissionAddQuery query) {
        RbacPermission parent;
        if (ROOT_ID.equals(query.getPid())) parent = ROOT_PERMISSION;
        else parent = permissionRep.findById(query.getPid())
                .orElseThrow(() -> new ShowToClientException("上级权限不存在"));

        if (parent.getPermissionType() != MENU)
            throw new ShowToClientException("上级权限必须是菜单类型");

        if (permissionRep.countByPermissionName(query.getPermissionName()) > 0)
            throw new ShowToClientException("权限代号[permissionCode]已存在");

        RbacPermission permission = BeanUtil.copyProperties(query, RbacPermission.class);
        permission
                .breadcrumbsBy(parent.getPid(), parent.getBreadcrumbs())
                .permissionLevelBy(parent.getPermissionLevel())
                .permissionNameBy(parent.getPermissionName());

        permissionRep.save(permission.setSortValue(DEFAULT_SORT_VALUE));
        return BeanUtil.copyProperties(permission, PermissionVO.class);
    }

    /**
     * 根据 ID 删除
     *
     * @param ids -
     * @return 删除数量
     */
    @Transactional
    public long deletePermissionsIdIn(Set<Integer> ids) {
        return permissionRep.deleteByIdIn(ids);
    }

    /**
     * 更新权限信息
     *
     * @param query 权限信息
     * @return 更新后的权限信息
     */
    @Transactional
    public PermissionVO updatePermission(PermissionUpdateQuery query) {
        RbacPermission permission = permissionRep.findById(query.getId())
                .orElseThrow(() -> new ShowToClientException("权限信息不存在"));

        if (permissionRep.countByPermissionName(query.getPermissionName()) > 0)
            throw new ShowToClientException("权限代号[permissionCode]已存在");

        BeanUtil.copyProperties(query, permission, CustomBeanUtils.IGNORE_NULL_COPY_OPTION);
        permissionRep.save(permission);
        return PermissionVO.of(permission);
    }

    /**
     * 根据权限ID查询
     *
     * @param permissionIds 权限ID
     * @return 权限列表
     */
    public List<RbacPermission> listPermissionsByUserId(Set<Integer> permissionIds) {
        return permissionRep.findAllById(permissionIds);
    }

    public PermissionService(PermissionRep permissionRep, RoleService roleService) {
        this.permissionRep = permissionRep;
        this.roleService = roleService;
    }
}
