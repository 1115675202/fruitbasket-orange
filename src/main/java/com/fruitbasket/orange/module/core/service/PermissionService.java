package com.fruitbasket.orange.module.core.service;

import com.fruitbasket.orange.module.core.pojo.entity.Permission;
import com.fruitbasket.orange.module.core.pojo.entity.Role;
import com.fruitbasket.orange.module.core.repository.PermissionRep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static java.util.Collections.emptyList;

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
        return !CollectionUtils.isEmpty(roles) ? permissionRep.queryAllByRolesInOrderBySortValueDesc(roles) : emptyList();
    }

    public PermissionService(PermissionRep permissionRep, RoleService roleService) {
        this.permissionRep = permissionRep;
        this.roleService = roleService;
    }
}
