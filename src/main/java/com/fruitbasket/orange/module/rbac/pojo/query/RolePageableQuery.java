package com.fruitbasket.orange.module.rbac.pojo.query;

import com.fruitbasket.orange.module.common.query.PageableQuery;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 权限分页查询
 *
 * @author LiuBing
 * @date 2021/4/21
 */
@Data
@Accessors(chain = true)
public class RolePageableQuery extends PageableQuery {

    /**
     * 显示在界面上的名称
     */
    private String roleShowName;
}
