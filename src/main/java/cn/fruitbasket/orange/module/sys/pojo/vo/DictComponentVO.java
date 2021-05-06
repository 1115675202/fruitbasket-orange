package cn.fruitbasket.orange.module.sys.pojo.vo;

import cn.fruitbasket.orange.module.sys.pojo.entity.SysDict;
import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * 字典组件
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Data
@Accessors(chain = true)
public class DictComponentVO {

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典显示名称
     */
    private String dictShowName;

    /**
     * 字典选项
     */
    private List<DictOptionComponentVO> dictOptions;

    public static DictComponentVO of(SysDict sysDict) {
        DictComponentVO dictComponentVO = BeanUtil.copyProperties(sysDict, DictComponentVO.class);
        dictComponentVO.setDictOptions(sysDict.getDictOptions().stream()
                .map(DictOptionComponentVO::of).collect(toList()));
        return dictComponentVO;
    }
}
