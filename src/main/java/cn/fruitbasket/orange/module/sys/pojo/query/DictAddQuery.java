package cn.fruitbasket.orange.module.sys.pojo.query;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 字典新增
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Data
@Accessors(chain = true)
public class DictAddQuery {

    /**
     * 字典名称
     */
    @NotBlank(message = "字典名称[dictName]：不能为空")
    @Size(min = 1, max = 20, message = "字典名称[dictName]：长度为 1 ~ 20")
    private String dictName;

    /**
     * 字典显示名称
     */

    @NotBlank(message = "字典显示名称[dictShowName]：不能为空")
    @Size(min = 1, max = 20, message = "字典显示名称[dictShowName]：长度为 1 ~ 20")
    private String dictShowName;

    /**
     * 字典选项
     */
    private List<DictOptionAddQuery> dictOptions;
}
