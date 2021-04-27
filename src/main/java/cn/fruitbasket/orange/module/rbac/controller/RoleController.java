package cn.fruitbasket.orange.module.rbac.controller;

import cn.fruitbasket.orange.module.common.vo.PageVO;
import cn.fruitbasket.orange.module.rbac.pojo.query.RoleAddQuery;
import cn.fruitbasket.orange.module.rbac.pojo.query.RoleBindPermissionsQuery;
import cn.fruitbasket.orange.module.rbac.pojo.query.RolePageableQuery;
import cn.fruitbasket.orange.module.rbac.pojo.query.RoleUpdateQuery;
import cn.fruitbasket.orange.module.rbac.pojo.vo.RoleDetailVO;
import cn.fruitbasket.orange.module.rbac.pojo.vo.RoleVO;
import cn.fruitbasket.orange.module.rbac.service.RoleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * 角色
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@Validated
@RestController
@RequestMapping("role")
public class RoleController {

    private final RoleService roleService;

    /**
     * @return 一页信息
     */
    @GetMapping("multi")
    public PageVO<RoleVO> listPageRoles(@Valid RolePageableQuery query) {
        return roleService.listPageRoles(query);
    }

    /**
     * 添加一个角色
     *
     * @param query 角色信息
     * @return 生成的角色信息
     */
    @PostMapping
    public RoleVO addRole(@RequestBody @Valid RoleAddQuery query) {
        return roleService.save(query);
    }

    /**
     * 根据角色 ID 删除多个角色
     *
     * @param roleIds -
     * @return 删除数量
     */
    @DeleteMapping("multi")
    public long deleteRolesIdIn(@RequestBody @Valid @NotEmpty(message = "角色ID：不能为空") Set<Integer> roleIds) {
        return roleService.deleteRolesIdIn(roleIds);
    }

    /**
     * 修改角色信息
     *
     * @param query 角色信息
     * @return 修改后的角色信息
     */
    @PutMapping
    public RoleVO updateRole(@RequestBody @Valid RoleUpdateQuery query) {
        return roleService.updateRole(query);
    }

    /**
     * 给角色绑定权限
     *
     * @param query 绑定信息
     */
    @PostMapping("permissions")
    public void bindingPermissions(@RequestBody @Valid RoleBindPermissionsQuery query) {
        roleService.bindingPermissions(query);
    }

    /**
     * 获取角色详情
     *
     * @param roleId 角色ID
     * @return -
     */
    @GetMapping("detail")
    public RoleDetailVO getRoleDetail(@NotNull(message = "角色ID[roleId]：不能为空") Integer roleId) {
        return roleService.getRoleDetail(roleId);
    }

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
}
