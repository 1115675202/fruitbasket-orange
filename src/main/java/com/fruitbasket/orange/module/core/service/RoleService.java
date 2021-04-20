package com.fruitbasket.orange.module.core.service;

import com.fruitbasket.orange.module.core.pojo.entity.Role;
import com.fruitbasket.orange.module.core.pojo.entity.User;
import com.fruitbasket.orange.module.core.repository.RoleRep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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

    /**
     * @param userId 用户ID
     * @return 用户所有角色
     */
    public List<Role> listRolesOf(Integer userId) {
        User user = new User();
        user.setId(userId);
        return roleRep.queryAllByUsersIsOrderBySortValueDesc(user);
    }

    /**
     * @param requestUrl 请求地址
     * @return 能访问该地址的角色
     */
    public List<Role> listBeAuthorizedRolesOf(String requestUrl) {
        return Collections.emptyList();
    }

    public RoleService(RoleRep roleRep) {
        this.roleRep = roleRep;
    }
}
