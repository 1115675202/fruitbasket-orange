package cn.fruitbasket.orange.module.sys.controller;

import cn.fruitbasket.orange.module.common.vo.PageVO;
import cn.fruitbasket.orange.module.sys.pojo.query.DictAddQuery;
import cn.fruitbasket.orange.module.sys.pojo.query.DictPageableQuery;
import cn.fruitbasket.orange.module.sys.pojo.query.DictUpdateQuery;
import cn.fruitbasket.orange.module.sys.pojo.vo.DictComponentVO;
import cn.fruitbasket.orange.module.sys.pojo.vo.DictVO;
import cn.fruitbasket.orange.module.sys.service.DictService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * 字典
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Validated
@RestController
@RequestMapping("dict")
public class DictController {

    private final DictService dictService;

    public DictController(DictService dictService) {
        this.dictService = dictService;
    }

    /**
     * @return 一页信息
     */
    @GetMapping("multi")
    public PageVO<DictVO> listPageDict(@Valid DictPageableQuery query) {
        return dictService.listPageDict(query);
    }

    /**
     * 添加一个字典
     *
     * @param query 字典信息
     * @return 生成的字典信息
     */
    @PostMapping
    public DictVO add(@RequestBody @Valid DictAddQuery query) {
        return dictService.saveDict(query);
    }

    /**
     * 根据字典 ID 删除多个字典
     *
     * @param dictIds -
     * @return 删除数量
     */
    @DeleteMapping("multi")
    public long deleteConfigIdIn(@RequestBody @Valid @NotEmpty(message = "字典ID：不能为空") Set<Integer> dictIds) {
        return dictService.deleteDictIdIn(dictIds);
    }

    /**
     * 修改配置信息
     *
     * @param query 配置信息
     * @return 修改后的配置信息
     */
    @PutMapping
    public DictVO updateDict(@RequestBody @Valid DictUpdateQuery query) {
        return dictService.updateDict(query);
    }

    /**
     * 获取字典组件信息
     *
     * @param dictName 字典名称
     * @return -
     */
    @GetMapping("component")
    public DictComponentVO getDictComponent(@Valid @NotBlank(message = "字典名称：不能为空") String dictName) {
        return dictService.getDict(dictName);
    }
}
