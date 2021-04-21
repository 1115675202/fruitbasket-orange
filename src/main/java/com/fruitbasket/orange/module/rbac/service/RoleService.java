package com.fruitbasket.orange.module.rbac.service;

import com.fruitbasket.orange.module.rbac.pojo.entity.RbacRole;
import com.fruitbasket.orange.module.rbac.pojo.entity.RbacUser;
import com.fruitbasket.orange.module.rbac.repository.RoleRep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public List<RbacRole> listRolesOf(Integer userId) {
        RbacUser user = new RbacUser();
        user.setId(userId);
        return roleRep.queryAllByUsersIsOrderBySortValueDesc(user);
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

    public RoleService(RoleRep roleRep) {
        this.roleRep = roleRep;
    }
}
