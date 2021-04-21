package com.fruitbasket.orange.module.rbac.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fruitbasket.orange.module.rbac.pojo.query.RolePageableQuery;
import com.fruitbasket.orange.module.rbac.pojo.vo.RoleVO;
import com.fruitbasket.orange.module.rbac.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@RequestMapping("role")
@RestController
public class RoleController {

    private final ObjectMapper objectMapper;

    private final RoleService roleService;

    /**
     * @return 登录信息
     */
    @GetMapping("list")
    public List<RoleVO> listRoles(RolePageableQuery query) {
        return roleService.listRoles(query);
    }


    public RoleController(ObjectMapper objectMapper, RoleService roleService) {
        this.objectMapper = objectMapper;
        this.roleService = roleService;
    }
}
