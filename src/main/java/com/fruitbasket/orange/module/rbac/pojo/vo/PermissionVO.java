package com.fruitbasket.orange.module.rbac.pojo.vo;

import cn.hutool.core.bean.BeanUtil;
import com.fruitbasket.orange.dict.PermissionType;
import com.fruitbasket.orange.module.common.vo.DataBaseVO;
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
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * 权限列表页面数据，是列表也是树状
 *
 * @author LiuBing
 * @date 2021/4/11
 */
@Data
@Accessors(chain = true)
public class PermissionVO extends DataBaseVO {

    /**
     * 父节点ID，0-无父节点
     */
    private Integer pid;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 显示在界面上的名称
     */
    private String permissionShowName;

    /**
     * 接口/地址
     */
    private String permissionLink;

    /**
     * 权限类型，用来区分菜单、接口、按钮等
     */
    private PermissionType permissionType;

    /**
     * 排序值
     */
    private Integer sortValue;

    /**
     * 备注
     */
    private String description;

    /**
     * 子菜单/权限
     */
    private List<PermissionVO> children;

    /**
     * 将权限列表按层级关系实例化成树形
     *
     * @param permissions 权限列表
     * @return 权限树形
     */
    public static PermissionVO treeOf(List<RbacPermission> permissions) {
        PermissionVO root = new PermissionVO();
        root.setId(ROOT_ID);

        if (CollectionUtils.isEmpty(permissions)) return root;

        Map<Integer, List<RbacPermission>> map = permissions.stream()
                .collect(groupingBy(RbacPermission::getPid, toList()));
        Queue<PermissionVO> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            for (int i = 0, n = queue.size(); i < n; i++) {
                PermissionVO node = queue.poll();
                if (isNull(node)) continue;
                List<PermissionVO> children = map.getOrDefault(node.getId(), emptyList()).stream()
                        .map(permission -> BeanUtil.copyProperties(permission, PermissionVO.class))
                        .peek(queue::offer).collect(toList());
                node.setChildren(children);
            }
        }

        if (isNull(root.children)) root.children = emptyList();
        return root;
    }

    public static PermissionVO of(RbacPermission permission) {
        return BeanUtil.copyProperties(permission, PermissionVO.class);
    }
}
