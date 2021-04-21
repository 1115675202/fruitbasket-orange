package com.fruitbasket.orange.module.rbac.service;

import com.fruitbasket.orange.module.rbac.pojo.entity.RbacRole;
import com.fruitbasket.orange.module.rbac.pojo.entity.RbacUser;
import com.fruitbasket.orange.module.rbac.pojo.query.RolePageableQuery;
import com.fruitbasket.orange.module.rbac.pojo.vo.RoleVO;
import com.fruitbasket.orange.module.rbac.repository.RoleRep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.springframework.data.domain.Sort.Direction.DESC;

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
        return roleRep.findAllByUsersIsOrderBySortValueDesc(user);
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

    public List<RoleVO> listRoles(RolePageableQuery query) {
        Pageable pageable = PageRequest.of(query.getPageNumber(), query.getPageSize(), Sort.by(DESC, ""));
        Page<RbacRole> page = roleRep.findAll(pageable);
        return page.get().map(RoleVO::of).collect(toList());
    }
}
