package cn.fruitbasket.orange.module.sys.pojo.entity;

import cn.fruitbasket.orange.module.common.entity.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import static cn.fruitbasket.orange.module.common.entity.BaseDO.NOT_DELETE_CONDITION;
import static cn.fruitbasket.orange.module.sys.pojo.entity.SysDict.TABLE_NAME;

/**
 * 字典
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Data
@Accessors(chain = true)
@Entity
@Where(clause = NOT_DELETE_CONDITION)
@SQLDelete(sql = "UPDATE " + TABLE_NAME + " SET deleted = true WHERE id = ?")
@SQLDeleteAll(sql = "UPDATE " + TABLE_NAME + " SET deleted = true WHERE id = ?")
public class SysDictOption extends BaseDO {

    static final String TABLE_NAME = "sys_dict_option";

    /**
     * 字典选项值
     */
    @Column(length = 20, nullable = false)
    private String optionValue;

    /**
     * 字典选项名称
     */
    @Column(length = 20, nullable = false)
    private String optionName;

    /**
     * 排序值
     */
    @Column(nullable = false)
    private Integer sortValue;

    @ManyToOne
    private SysDict dict;
}
