package cn.fruitbasket.orange.module.rbac.pojo.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.fruitbasket.orange.module.rbac.pojo.entity.RbacPermission;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static cn.fruitbasket.orange.module.rbac.pojo.entity.RbacPermission.ROOT_ID;
import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.*;

/**
 * 角色权限绑定界面
 *
 * @author LiuBing
 * @date 2021/4/11
 */
@Data
@Accessors(chain = true)
public class RolePermissionTreeNodeVO {

    /**
     * ID
     */
    private Integer id;

    /**
     * 菜单/权限名称
     */
    private String permissionName;

    /**
     * true-已与角色绑定
     */
    private Boolean bound;

    /**
     * 子权限
     */
    private List<RolePermissionTreeNodeVO> children;

    /**
     * 将权限列表按层级关系实例化成树形，并且标记是否与角色绑定
     *
     * @param allPermissions   所有权限
     * @param boundPermissions 与角色绑定的权限
     * @return 顶级 Root 节点
     */
    public static RolePermissionTreeNodeVO treeOf(
            Collection<RbacPermission> allPermissions, Collection<RbacPermission> boundPermissions) {
        RolePermissionTreeNodeVO root = new RolePermissionTreeNodeVO();
        root.setId(ROOT_ID).setChildren(emptyList());

        if (CollectionUtils.isEmpty(allPermissions)) return root;

        Set<Integer> boundPermissionIds = boundPermissions.stream()
                .map(RbacPermission::getId).collect(toSet());

        Map<Integer, List<RbacPermission>> map = allPermissions.stream()
                .collect(groupingBy(RbacPermission::getPid, toList()));
        Queue<RolePermissionTreeNodeVO> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            for (int i = 0, n = queue.size(); i < n; i++) {
                RolePermissionTreeNodeVO node = queue.poll();
                if (isNull(node)) continue;
                List<RolePermissionTreeNodeVO> children = map.getOrDefault(node.getId(), emptyList()).stream()
                        .map(permission -> {
                            RolePermissionTreeNodeVO vo = of(permission);
                            if (boundPermissionIds.contains(vo.getId())) vo.setBound(true);
                            return vo;
                        })
                        .peek(queue::offer).collect(toList());
                node.setChildren(children);
            }
        }

        if (isNull(root.children)) root.children = emptyList();
        return root;
    }

    public static RolePermissionTreeNodeVO of(RbacPermission permission) {
        return BeanUtil.copyProperties(permission, RolePermissionTreeNodeVO.class);
    }
}
