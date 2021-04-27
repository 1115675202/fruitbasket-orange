package cn.fruitbasket.orange.module.sys.pojo.query;

import cn.fruitbasket.orange.module.common.query.PageableQuery;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 系统配置分页查询
 *
 * @author LiuBing
 * @date 2021/4/21
 */
@Data
@Accessors(chain = true)
public class ConfigPageableQuery extends PageableQuery {

    /**
     * 配置键
     */
    private String configKey;
}
