package cn.fruitbasket.orange.module.sys.pojo.query;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 系统配置新增
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Data
@Accessors(chain = true)
public class ConfigUpdateQuery {

    /**
     * 配置 ID
     */
    @NotNull
    private Integer id;

    /**
     * 配置值
     */
    @Size(min = 1, max = 100, message = "配置值[configValue]：长度为 1 ~ 100")
    private String configValue;

    /**
     * 备注
     */
    private String description;
}
