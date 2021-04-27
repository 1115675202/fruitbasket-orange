package cn.fruitbasket.orange.module.common.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 数据时间信息
 *
 * @author LiuBing
 * @date 2021/4/21
 */
@Data
@Accessors(chain = true)
public class BaseDataVO {

    /**
     * 唯一 ID
     */
    private Integer id;

    /**
     * true-已删除
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 最后修改时间
     */
    private LocalDateTime gmtModified;
}
