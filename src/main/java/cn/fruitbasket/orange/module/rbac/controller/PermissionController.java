package cn.fruitbasket.orange.module.rbac.controller;

import cn.fruitbasket.orange.module.rbac.pojo.query.PermissionAddQuery;
import cn.fruitbasket.orange.module.rbac.pojo.query.PermissionUpdateQuery;
import cn.fruitbasket.orange.module.rbac.pojo.vo.PermissionVO;
import cn.fruitbasket.orange.module.rbac.service.PermissionService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

/**
 * 权限
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@Validated
@RestController
@RequestMapping("permission")
public class PermissionController {

    private final PermissionService permissionService;

    /**
     * 列出所有权限信息
     *
     * @return 权限树
     */
    @GetMapping("tree")
    public List<PermissionVO> treeOfPermissions() {
        return permissionService.treeOfAllPermissions();
    }

    /**
     * 添加一个权限信息
     *
     * @param query 权限信息
     * @return 生成的权限信息
     */
    @PostMapping
    public PermissionVO addPermission(@RequestBody @Valid PermissionAddQuery query) {
        return permissionService.save(query);
    }

    /**
     * 根据权限 ID 删除多个权限
     *
     * @param ids -
     * @return 删除数量
     */
    @DeleteMapping("multi")
    public long deletePermissionsIdIn(@RequestBody @Valid @NotEmpty(message = "权限ID：不能为空") Set<Integer> ids) {
        return permissionService.deletePermissionsIdIn(ids);
    }

    /**
     * 修改权限信息
     *
     * @param query 权限信息
     * @return 修改后的权限信息
     */
    @PutMapping
    public PermissionVO updatePermission(@RequestBody @Valid PermissionUpdateQuery query) {
        return permissionService.updatePermission(query);
    }

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }
}
