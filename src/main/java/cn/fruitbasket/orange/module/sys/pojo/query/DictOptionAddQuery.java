package cn.fruitbasket.orange.module.sys.pojo.query;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 字典选项新增
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Data
@Accessors(chain = true)
public class DictOptionAddQuery {

    /**
     * 字典选项值
     */
    @NotBlank(message = "字典选项值[optionValue]：不能为空")
    @Size(min = 1, max = 20, message = "字典选项值[optionValue]：长度为 1 ~ 20")
    private String optionValue;

    /**
     * 字典选项名称
     */
    @NotBlank(message = "字典选项名称[optionName]：不能为空")
    @Size(min = 1, max = 20, message = "字典选项名称[optionName]：长度为 1 ~ 20")
    private String optionName;

    /**
     * 排序值
     */
    private Integer sortValue;
}
