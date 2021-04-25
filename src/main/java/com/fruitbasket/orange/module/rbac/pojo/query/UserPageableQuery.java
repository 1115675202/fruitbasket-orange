package com.fruitbasket.orange.module.rbac.pojo.query;

import com.fruitbasket.orange.module.common.query.PageableQuery;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色分页查询
 *
 * @author LiuBing
 * @date 2021/4/21
 */
@Data
@Accessors(chain = true)
public class UserPageableQuery extends PageableQuery {

    /**
     * 真实姓名
     */
    private String realName;
}
