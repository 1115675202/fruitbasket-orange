package com.fruitbasket.orange.module.rbac.service;

import cn.hutool.core.bean.BeanUtil;
import com.fruitbasket.orange.exception.BusinessException;
import com.fruitbasket.orange.module.rbac.pojo.entity.RbacPermission;
import com.fruitbasket.orange.module.rbac.pojo.entity.RbacRole;
import com.fruitbasket.orange.module.rbac.pojo.query.PermissionAddQuery;
import com.fruitbasket.orange.module.rbac.pojo.query.PermissionUpdateQuery;
import com.fruitbasket.orange.module.rbac.pojo.vo.PermissionVO;
import com.fruitbasket.orange.module.rbac.repository.PermissionRep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

import static com.fruitbasket.orange.dict.PermissionType.MENU;
import static com.fruitbasket.orange.module.rbac.pojo.entity.RbacPermission.*;
import static com.fruitbasket.orange.util.CustomBeanUtils.IGNORE_NULL_COPY_OPTION;
import static java.util.Collections.emptyList;
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
public class PermissionService {

    private final PermissionRep permissionRep;

    private final RoleService roleService;

    /**
     * 列出用户拥有的权限
     *
     * @param userId 用户 ID
     * @return 权限列表
     */
    public List<RbacPermission> listPermissionsBy(Integer userId) {
        List<RbacRole> roles = roleService.listRolesOf(userId);
        return CollectionUtils.isEmpty(roles) ? emptyList() :
                roles.stream().flatMap(role -> role.getPermissions().stream()).collect(toList());
    }

    /**
     * @return 权限列表数据
     */
    public List<PermissionVO> treeOfPermissions() {
        return PermissionVO.treeOf(permissionRep.findAll()).getChildren();
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
                .orElseThrow(() -> new BusinessException("上级权限不存在"));

        if (parent.getPermissionType() != MENU)
            throw new BusinessException("上级权限必须是菜单类型");

        if (permissionRep.countByPermissionName(query.getPermissionName()) > 0)
            throw new BusinessException("权限名称[permissionName]已存在");

        RbacPermission permission = new RbacPermission()
                .setBreadcrumbs(breadcrumbsBy(parent))
                .setPermissionLevel(permissionLevelBy(parent.getPermissionLevel()));
        BeanUtil.copyProperties(query, permission, IGNORE_NULL_COPY_OPTION);
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
                .orElseThrow(() -> new BusinessException("权限信息不存在"));

        if (hasText(query.getPermissionName())
                && permissionRep.countByPermissionName(query.getPermissionName()) > 0) {
            throw new BusinessException("权限名称[permissionName]已存在");
        }

        BeanUtil.copyProperties(query, permission, IGNORE_NULL_COPY_OPTION);
        permissionRep.save(permission);
        return PermissionVO.of(permission);
    }

    public PermissionService(PermissionRep permissionRep, RoleService roleService) {
        this.permissionRep = permissionRep;
        this.roleService = roleService;
    }
}
