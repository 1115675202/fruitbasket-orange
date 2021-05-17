package cn.fruitbasket.orange.module.common.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

import static org.springframework.data.util.CastUtils.cast;

/**
 * 数据时间信息
 *
 * @author LiuBing
 * @date 2021/4/21
 * @param <C> 子类型调用本类中的set()方法后返回子类型，实现 set() 链式调用
 */
@Data
@Accessors(chain = true)
public class BaseDataVO<C extends BaseDataVO<?>> {

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

    public C setId(Integer id) {
        this.id = id;
        return cast(this);
    }

    public C setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return cast(this);
    }

    public C setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
        return cast(this);
    }

    public C setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
        return cast(this);
    }
}
