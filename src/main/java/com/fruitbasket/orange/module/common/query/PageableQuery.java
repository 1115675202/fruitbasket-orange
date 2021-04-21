package com.fruitbasket.orange.module.common.query;

import lombok.Data;
import lombok.experimental.Accessors;

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
    private Integer pageNumber;

    /**
     * 一页数据行数
     */
    private Integer pageSize;
}
