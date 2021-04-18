package com.fruitbasket.orange.module.core.service;

import com.fruitbasket.orange.module.core.pojo.entity.Role;
import com.fruitbasket.orange.module.core.pojo.entity.User;
import com.fruitbasket.orange.module.core.repository.RoleRep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
     * 查询用户所有角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    public List<Role> listRolesBy(Integer userId) {
        User user = new User();
        user.setId(userId);
        return roleRep.queryAllByUsersIsOrderBySortValueDesc(user);
    }

    public RoleService(RoleRep roleRep) {
        this.roleRep = roleRep;
    }
}
