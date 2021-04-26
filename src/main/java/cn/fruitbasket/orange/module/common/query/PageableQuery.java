package cn.fruitbasket.orange.module.common.query;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 通用分页接收体
 *
 * @author LiuBing
 * @date 2021/4/21
 */
@Data
@Accessors(chain = true)
public class PageableQuery {

    /**
     * 页码
     */
    @NotNull(message = "页码[pageNumber]：不能为空")
    @Min(value = 0, message = "行数[pageSize]：最小值为 0")
    private Integer pageNumber;

    /**
     * 一页数据行数
     */
    @NotNull(message = "行数[pageSize]：不能为空")
    @Min(value = 1, message = "行数[pageSize]：最小值为 1")
    private Integer pageSize;
}
