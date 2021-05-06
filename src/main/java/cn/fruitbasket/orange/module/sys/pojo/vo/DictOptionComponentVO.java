package cn.fruitbasket.orange.module.sys.pojo.vo;

import cn.fruitbasket.orange.module.sys.pojo.entity.SysDictOption;
import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 字典组件选项
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Data
@Accessors(chain = true)
public class DictOptionComponentVO {

    /**
     * 字典选项值
     */
    private String optionValue;

    /**
     * 字典选项名称
     */
    private String optionName;

    /**
     * 排序值
     */
    private Integer sortValue;

    public static DictOptionComponentVO of(SysDictOption sysDictOption) {
        return BeanUtil.copyProperties(sysDictOption, DictOptionComponentVO.class);
    }
}
