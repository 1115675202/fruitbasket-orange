package cn.fruitbasket.orange.module.sys.pojo.vo;

import cn.fruitbasket.orange.module.common.vo.BaseDataVO;
import cn.fruitbasket.orange.module.sys.pojo.entity.SysDict;
import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * 字典
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Data
@Accessors(chain = true)
public class DictVO extends BaseDataVO<DictVO> {

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
    private List<DictOptionVO> dictOptions;

    public static DictVO of(SysDict sysDict) {
        DictVO dictVO = BeanUtil.copyProperties(sysDict, DictVO.class);
        dictVO.setDictOptions(sysDict.getDictOptions().stream().map(DictOptionVO::of).collect(toList()));
        return dictVO;
    }
}
