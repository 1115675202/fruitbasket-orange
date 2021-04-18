package com.fruitbasket.orange.module.core.service;

import com.fruitbasket.orange.config.security.CustomUserDetails;
import com.fruitbasket.orange.module.core.pojo.entity.Permission;
import com.fruitbasket.orange.module.core.pojo.entity.User;
import com.fruitbasket.orange.module.core.pojo.vo.MenuTreeNodeVO;
import com.fruitbasket.orange.module.core.repository.UserAccountRep;
import com.fruitbasket.orange.module.core.repository.UserRep;
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

	private final PermissionService permissionService;

	public User getUserBy(String username) {



		return null;
	}

	public List<MenuTreeNodeVO> getPermissionTree() {
		List<Permission> permissions = permissionService.listPermissionsBy(getLoginInfo().getUserId());
		return MenuTreeNodeVO.treeOf(permissions);
	}

	public CustomUserDetails getLoginInfo() {
		return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public UserService(UserRep userRep, UserAccountRep userAccountRep, PermissionService permissionService) {
		this.userRep = userRep;
		this.userAccountRep = userAccountRep;
		this.permissionService = permissionService;
	}
}
