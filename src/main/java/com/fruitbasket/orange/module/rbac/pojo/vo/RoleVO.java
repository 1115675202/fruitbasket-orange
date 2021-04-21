package com.fruitbasket.orange.module.rbac.pojo.vo;

import com.fruitbasket.orange.dict.PermissionType;
import com.fruitbasket.orange.module.common.vo.DataTimeVO;
import com.fruitbasket.orange.module.common.vo.PageableDataTimeVO;
import com.fruitbasket.orange.module.rbac.pojo.entity.RbacRole;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

/**
 * @author LiuBing
 * @date 2021/4/21
 */
@Data
@Accessors(chain = true)
public class RoleVO extends PageableDataTimeVO {

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
    private Byte sortValue;

    /**
     * 备注
     */
    private String description;

    public static RoleVO of(RbacRole rbacRole) {
        RoleVO roleVO = new RoleVO();
        BeanUtils.copyProperties(rbacRole, roleVO);
        return roleVO;
    }
}
