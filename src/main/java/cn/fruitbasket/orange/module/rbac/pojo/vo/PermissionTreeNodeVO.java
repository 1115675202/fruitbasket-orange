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
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * 主界面左边的菜单栏，包含 菜单 和 权限（按钮）
 *
 * @author LiuBing
 * @date 2021/4/11
 */
@Data
@Accessors(chain = true)
public class PermissionTreeNodeVO {

    /**
     * ID
     */
    private Integer id;

    /**
     * 菜单/权限名称
     */
    private String permissionName;

    /**
     * 菜单/权限地址
     */
    private String permissionLink;

    /**
     * 子权限
     */
    private List<PermissionTreeNodeVO> children;

    /**
     * 将权限列表按层级关系实例化成树形
     *
     * @param permissions 权限列表
     * @return 顶级 Root 节点
     */
    public static PermissionTreeNodeVO treeOf(Collection<RbacPermission> permissions) {
        PermissionTreeNodeVO root = new PermissionTreeNodeVO()
                .setId(ROOT_ID).setChildren(emptyList());

        if (CollectionUtils.isEmpty(permissions)) return root;

        Map<Integer, List<RbacPermission>> map = permissions.stream()
                .collect(groupingBy(RbacPermission::getPid, toList()));
        Queue<PermissionTreeNodeVO> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            for (int i = 0, n = queue.size(); i < n; i++) {
                PermissionTreeNodeVO node = queue.poll();
                if (isNull(node)) continue;
                List<PermissionTreeNodeVO> children = map.getOrDefault(node.getId(), emptyList())
                        .stream().map(PermissionTreeNodeVO::of)
                        .peek(queue::offer).collect(toList());
                node.setChildren(children);
            }
        }

        return root;
    }

    public static PermissionTreeNodeVO of(RbacPermission permission) {
        return BeanUtil.copyProperties(permission, PermissionTreeNodeVO.class);
    }
}
