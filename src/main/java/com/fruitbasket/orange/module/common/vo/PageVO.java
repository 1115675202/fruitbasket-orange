package com.fruitbasket.orange.module.common.vo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * 数据时间信息
 *
 * @author LiuBing
 * @date 2021/4/21
 */
@Data
@Accessors(chain = true)
public class PageVO<T> {

    /**
     * 实际个数
     */
    private Integer elementCount;

    /**
     * 一页个数
     */
    private Integer pageSize;

    /**
     * 本页页码
     */
    private Integer pageNumber;

    /**
     * 总页数
     */
    private Integer totalPages;

    /**
     * 数据
     */
    private List<T> data;

    /**
     *
     * @param page 源分页数据
     * @param converter 源-页面展示类型转换器
     * @param <S> 源类型
     * @param <T> 页面展示类型
     * @return 分页数据
     */
    public static <S,T> PageVO<T> of(Page<S> page, Function<S, T> converter) {
        return new PageVO<T>().setElementCount(page.getNumberOfElements())
                .setPageSize(page.getSize())
                .setPageNumber(page.getNumber())
                .setTotalPages(page.getTotalPages())
                .setData(page.get().map(converter).collect(toList()));
    }
}
