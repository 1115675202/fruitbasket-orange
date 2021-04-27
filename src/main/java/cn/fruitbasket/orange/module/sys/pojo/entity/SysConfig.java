package cn.fruitbasket.orange.module.sys.pojo.entity;

import cn.fruitbasket.orange.module.common.entity.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;

import static cn.fruitbasket.orange.module.common.entity.BaseDO.NOT_DELETE_CONDITION;
import static cn.fruitbasket.orange.module.sys.pojo.entity.SysConfig.TABLE_NAME;

/**
 * 普通配置
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
public class SysConfig extends BaseDO {

    static final String TABLE_NAME = "sys_config";

    /**
     * 配置键
     */
    @Column(length = 50, nullable = false, unique = true)
    private String configKey;

    /**
     * 配置值
     */
    @Column(length = 100, nullable = false)
    private String configValue;

    /**
     * 备注
     */
    @Column(length = 100)
    private String description;
}
