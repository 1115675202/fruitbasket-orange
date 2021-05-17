package cn.fruitbasket.orange.module.rbac.pojo.vo;

import cn.fruitbasket.orange.module.common.vo.BaseDataVO;
import cn.fruitbasket.orange.module.rbac.pojo.entity.RbacRole;
import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 分页返回
 *
 * @author LiuBing
 * @date 2021/4/21
 */
@Data
@Accessors(chain = true)
public class RoleVO extends BaseDataVO<RoleVO> {

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色显示名称
     */
    private String roleShowName;

    /**
     * 排序值
     */
    private Integer sortValue;

    /**
     * 备注
     */
    private String description;

    public static RoleVO of(RbacRole rbacRole) {
        return BeanUtil.copyProperties(rbacRole, RoleVO.class);
    }
}
