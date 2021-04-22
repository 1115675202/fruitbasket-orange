package com.fruitbasket.orange.module.rbac.pojo.vo;

import cn.hutool.core.bean.BeanUtil;
import com.fruitbasket.orange.module.rbac.pojo.entity.RbacPermission;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static com.fruitbasket.orange.module.rbac.pojo.entity.RbacPermission.ROOT_ID;
import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * 菜单/权限
 *
 * @author LiuBing
 * @date 2021/4/11
 */
@Data
@Accessors(chain = true)
public class MenuTreeNodeVO {

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
    private String permissionUrl;

    /**
     * 子菜单/权限
     */
    private List<MenuTreeNodeVO> children;

    /**
     * 将权限列表按层级关系实例化成树形
     *
     * @param permissions 权限列表
     * @return 权限树形结构
     */
    public static List<MenuTreeNodeVO> treeOf(List<RbacPermission> permissions) {
        if (CollectionUtils.isEmpty(permissions)) {
            return emptyList();
        }

        Map<Integer, List<RbacPermission>> map = permissions.stream().collect(groupingBy(RbacPermission::getPid, toList()));

        MenuTreeNodeVO root = new MenuTreeNodeVO().setId(ROOT_ID);
        Queue<MenuTreeNodeVO> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            for (int i = 0, n = queue.size(); i < n; i++) {
                MenuTreeNodeVO node = queue.poll();
                if (isNull(node)) {
                    continue;
                }
                List<MenuTreeNodeVO> children = map.getOrDefault(node.getId(), emptyList()).stream()
                        .map(permission -> {
                            MenuTreeNodeVO child = new MenuTreeNodeVO();
                            BeanUtil.copyProperties(permission, child);
                            return child;
                        }).peek(queue::offer).collect(toList());
                node.setChildren(children);
            }
        }
        return nonNull(root.children) ? root.children : emptyList();
    }
}
