package com.fruitbasket.orange.module.core.service;

import com.fruitbasket.orange.dict.PermissionType;
import com.fruitbasket.orange.module.core.pojo.entity.Permission;
import com.fruitbasket.orange.module.core.pojo.entity.Role;
import com.fruitbasket.orange.module.core.repository.PermissionRep;
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

    public List<Permission> listPermissionsBy(Integer userId) {
        List<Role> roles = roleService.listRolesBy(userId);
        return CollectionUtils.isEmpty(roles) ? emptyList() :
                roles.stream().flatMap(role -> role.getPermissions().stream()).collect(toList());
    }

    public List<Permission> listPermissionsBy(List<Role> roles, List<PermissionType> types) {
        return CollectionUtils.isEmpty(roles) ? emptyList() :
                permissionRep.queryAllByRolesInAndPermissionTypeIn(roles, types);
    }

    public PermissionService(PermissionRep permissionRep, RoleService roleService) {
        this.permissionRep = permissionRep;
        this.roleService = roleService;
    }
}
