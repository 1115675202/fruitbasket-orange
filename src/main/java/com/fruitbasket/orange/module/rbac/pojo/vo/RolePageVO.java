package com.fruitbasket.orange.module.rbac.pojo.vo;

import cn.hutool.core.bean.BeanUtil;
import com.fruitbasket.orange.module.common.vo.DataBaseVO;
import com.fruitbasket.orange.module.rbac.pojo.entity.RbacRole;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 分页返回
 * @author LiuBing
 * @date 2021/4/21
 */
@Data
@Accessors(chain = true)
public class RolePageVO extends DataBaseVO {

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 显示在界面上的名称
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

    public static RolePageVO of(RbacRole rbacRole) {
        RolePageVO rolePageVO = new RolePageVO();
        BeanUtil.copyProperties(rbacRole, rolePageVO);
        return rolePageVO;
    }
}
