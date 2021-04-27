package cn.fruitbasket.orange.module.sys.pojo.query;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 系统配置新增
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Data
@Accessors(chain = true)
public class ConfigAddQuery {

    /**
     * 配置键
     */
    @NotBlank(message = "配置键[configKey]：不能为空")
    @Size(min = 1, max = 50, message = "配置键[configKey]：长度为 1 ~ 50")
    private String configKey;

    /**
     * 配置值
     */
    @NotBlank(message = "配置值[configValue]：不能为空")
    @Size(min = 1, max = 100, message = "配置值[configValue]：长度为 1 ~ 100")
    private String configValue;

    /**
     * 备注
     */
    private String description;
}
