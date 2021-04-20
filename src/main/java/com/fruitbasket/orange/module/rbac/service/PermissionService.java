package com.fruitbasket.orange.module.rbac.service;

import com.fruitbasket.orange.dict.PermissionType;
import com.fruitbasket.orange.module.rbac.pojo.entity.RbacPermission;
import com.fruitbasket.orange.module.rbac.pojo.entity.RbacRole;
import com.fruitbasket.orange.module.rbac.repository.PermissionRep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

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

    public List<RbacPermission> listPermissionsBy(Integer userId) {
        List<RbacRole> roles = roleService.listRolesOf(userId);
        return CollectionUtils.isEmpty(roles) ? emptyList() :
                roles.stream().flatMap(role -> role.getPermissions().stream()).collect(toList());
    }

    public List<RbacPermission> listPermissionsBy(List<RbacRole> roles, List<PermissionType> types) {
        return CollectionUtils.isEmpty(roles) ? emptyList() :
                permissionRep.queryAllByRolesInAndPermissionTypeIn(roles, types);
    }

    public PermissionService(PermissionRep permissionRep, RoleService roleService) {
        this.permissionRep = permissionRep;
        this.roleService = roleService;
    }
}
