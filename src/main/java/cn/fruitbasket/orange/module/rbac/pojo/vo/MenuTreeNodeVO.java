package cn.fruitbasket.orange.module.rbac.pojo.vo;

import cn.fruitbasket.orange.dict.PermissionType;
import cn.fruitbasket.orange.module.rbac.pojo.entity.RbacPermission;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static cn.fruitbasket.orange.dict.PermissionType.API;
import static cn.fruitbasket.orange.dict.PermissionType.MENU;
import static cn.fruitbasket.orange.module.rbac.pojo.entity.RbacPermission.ROOT_ID;
import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.*;

/**
 * 主界面左边的菜单栏，包含 菜单 和 权限（按钮）
 *
 * @author LiuBing
 * @date 2021/4/11
 */
@Data
@Accessors(chain = true)
public class MenuTreeNodeVO {

    /**
     * 权限 ID
     */
    private Integer id;

    /**
     * 菜单/权限名称
     */
    private String menuName;

    /**
     * 权限显示名称
     */
    private String menuShowName;

    /**
     * 图标
     */
    private String icon;

    /**
     * 菜单下的按钮列表
     */
    private Set<MenuButtonNodeVO> buttons;

    /**
     * 子权限
     */
    private List<MenuTreeNodeVO> children;

    /**
     * 将权限列表按层级关系实例化成树形
     *
     * @param permissions 权限列表
     * @return 顶级 Root 节点
     */
    public static MenuTreeNodeVO treeOf(Collection<RbacPermission> permissions) {
        MenuTreeNodeVO root = new MenuTreeNodeVO()
                .setId(ROOT_ID).setChildren(emptyList());

        if (CollectionUtils.isEmpty(permissions)) return root;

        // 按父节点ID分组
        Map<Integer, List<RbacPermission>> permissionPidMap = permissions.stream()
                .collect(groupingBy(RbacPermission::getPid, toList()));

        Queue<MenuTreeNodeVO> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            for (int i = 0, n = queue.size(); i < n; i++) {
                MenuTreeNodeVO node = queue.poll();
                if (isNull(node)) continue;

                // 按权限类型分组
                Map<PermissionType, List<RbacPermission>> permissionTypeMap = permissionPidMap
                        .getOrDefault(node.getId(), emptyList())
                        .stream()
                        .collect(groupingBy(RbacPermission::getPermissionType));

                // 转换接口类型权限为按钮
                node.setButtons(
                        permissionTypeMap
                                .getOrDefault(API, emptyList())
                                .stream()
                                .map(MenuButtonNodeVO::of)
                                .collect(toSet())
                );

                // 转换菜单类型权限为子节点
                List<MenuTreeNodeVO> children = permissionTypeMap
                        .getOrDefault(MENU, emptyList())
                        .stream()
                        .map(MenuTreeNodeVO::of)
                        .collect(toList());
                node.setChildren(children);
                children.forEach(queue::offer);
            }
        }

        return root;
    }

    public static MenuTreeNodeVO of(RbacPermission permission) {
        return new MenuTreeNodeVO()
                .setId(permission.getId())
                .setMenuName(permission.getPermissionName())
                .setMenuShowName(permission.getPermissionShowName())
                .setIcon(permission.getIcon());
    }
}
