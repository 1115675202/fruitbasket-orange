package cn.fruitbasket.orange.module.sys.pojo.query;

import cn.fruitbasket.orange.module.common.query.PageableQuery;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 字典分页查询
 *
 * @author LiuBing
 * @date 2021/4/21
 */
@Data
@Accessors(chain = true)
public class DictPageableQuery extends PageableQuery {

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典显示名称
     */
    private String dictShowName;
}
