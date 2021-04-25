package com.fruitbasket.orange.module.rbac.controller;

import com.fruitbasket.orange.module.common.vo.PageVO;
import com.fruitbasket.orange.module.rbac.pojo.query.RoleAddQuery;
import com.fruitbasket.orange.module.rbac.pojo.query.RoleUpdateQuery;
import com.fruitbasket.orange.module.rbac.pojo.query.RolePageableQuery;
import com.fruitbasket.orange.module.rbac.pojo.vo.RolePageVO;
import com.fruitbasket.orange.module.rbac.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@RequestMapping("role")
@RestController
public class RoleController {

    private final RoleService roleService;

    /**
     * @return 一页信息
     */
    @GetMapping("multi")
    public PageVO<RolePageVO> listPageRoles(@Valid RolePageableQuery query) {
        return roleService.listPageRoles(query);
    }

    /**
     * 添加一个角色
     * @param query 角色信息
     * @return 生成的角色信息
     */
    @PostMapping
    public RolePageVO addRole(@RequestBody RoleAddQuery query) {
        return roleService.save(query);
    }

    /**
     * 根据角色 ID 删除多个角色
     * @param ids -
     * @return 删除数量
     */
    @DeleteMapping("multi")
    public long deleteRolesIdIn(@RequestBody @Valid @NotEmpty(message = "角色ID：不能为空") Set<Integer> ids) {
        return roleService.deleteRolesIdIn(ids);
    }

    /**
     * 修改角色信息
     * @param query 角色信息
     * @return 修改后的角色信息
     */
    @PutMapping
    public RolePageVO updateRole(@RequestBody @Valid RoleUpdateQuery query) {
        return roleService.updateRole(query);
    }

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
}
