package cn.fruitbasket.orange.module.sys.pojo.entity;

import cn.fruitbasket.orange.module.common.entity.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

import static cn.fruitbasket.orange.module.common.entity.BaseDO.NOT_DELETE_CONDITION;
import static cn.fruitbasket.orange.module.sys.pojo.entity.SysDict.TABLE_NAME;
import static javax.persistence.CascadeType.ALL;

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
public class SysDict extends BaseDO<SysDict> {

    static final String TABLE_NAME = "sys_dict";

    /**
     * 字典名称
     */
    @Column(length = 20, nullable = false)
    private String dictName;

    /**
     * 字典显示名称
     */
    @Column(length = 20, nullable = false)
    private String dictShowName;

    @OneToMany(mappedBy = "dict", cascade = ALL)
    private List<SysDictOption> dictOptions;
}
