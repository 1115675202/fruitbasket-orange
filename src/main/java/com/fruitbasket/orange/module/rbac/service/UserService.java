package com.fruitbasket.orange.module.rbac.service;

import com.fruitbasket.orange.config.security.CustomUserDetails;
import com.fruitbasket.orange.module.rbac.pojo.entity.RbacPermission;
import com.fruitbasket.orange.module.rbac.pojo.entity.RbacUserAccount;
import com.fruitbasket.orange.module.rbac.pojo.vo.MenuTreeNodeVO;
import com.fruitbasket.orange.module.rbac.repository.UserAccountRep;
import com.fruitbasket.orange.module.rbac.repository.UserRep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@Slf4j
@Service
public class UserService {

    private final UserRep userRep;

    private final UserAccountRep userAccountRep;

    private final RoleService roleService;

    private final PermissionService permissionService;

    /**
     * @param username 账户标识/账号
     * @return 账户及用户信息
     */
    public RbacUserAccount getUserAccountBy(String username) {
        return userAccountRep.getUserAccountByIdentifier(username);
    }

    /**
     * @return 菜单树形数据
     */
    public List<MenuTreeNodeVO> getMenuTree() {
        List<RbacPermission> permissions = permissionService.listPermissionsBy(getLoginInfo().getUserId());
        return MenuTreeNodeVO.treeOf(permissions);
    }

    public CustomUserDetails getLoginInfo() {
        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public UserService(UserRep userRep,
                       UserAccountRep userAccountRep,
                       RoleService roleService,
                       PermissionService permissionService) {
        this.userRep = userRep;
        this.userAccountRep = userAccountRep;
        this.roleService = roleService;
        this.permissionService = permissionService;
    }
}
