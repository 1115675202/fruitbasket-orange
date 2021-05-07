package cn.fruitbasket.orange.module.rbac.pojo.vo;

import cn.fruitbasket.orange.dict.PermissionType;
import cn.fruitbasket.orange.module.common.vo.BaseDataVO;
import cn.fruitbasket.orange.module.rbac.pojo.entity.RbacPermission;
import cn.hutool.core.bean.BeanUtil;
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
 * 权限列表页面数据，是列表也是树状
 *
 * @author LiuBing
 * @date 2021/4/11
 */
@Data
@Accessors(chain = true)
public class PermissionVO extends BaseDataVO {

    /**
     * 父节点ID，0-无父节点
     */
    private Integer pid;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限显示名称
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
     * 图标
     */
    private String icon;

    /**
     * 排序值
     */
    private Integer sortValue;

    /**
     * 备注
     */
    private String description;

    /**
     * 子权限
     */
    private List<PermissionVO> children;

    /**
     * 将权限列表按层级关系实例化成树形
     *
     * @param permissions 权限列表
     * @return 权限树形
     */
    public static PermissionVO treeOf(Collection<RbacPermission> permissions) {
        PermissionVO root = new PermissionVO().setChildren(emptyList());
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
                List<PermissionVO> children = map.getOrDefault(node.getId(), emptyList())
                        .stream().map(PermissionVO::of).collect(toList());
                node.setChildren(children);
                children.forEach(queue::offer);
            }
        }

        return root;
    }

    public static PermissionVO of(RbacPermission permission) {
        return BeanUtil.copyProperties(permission, PermissionVO.class);
    }
}
