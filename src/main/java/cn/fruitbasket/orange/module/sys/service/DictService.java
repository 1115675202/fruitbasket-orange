package cn.fruitbasket.orange.module.sys.service;

import cn.fruitbasket.orange.config.exception.ShowToClientException;
import cn.fruitbasket.orange.module.common.vo.PageVO;
import cn.fruitbasket.orange.module.sys.pojo.entity.SysDict;
import cn.fruitbasket.orange.module.sys.pojo.entity.SysDictOption;
import cn.fruitbasket.orange.module.sys.pojo.query.DictAddQuery;
import cn.fruitbasket.orange.module.sys.pojo.query.DictPageableQuery;
import cn.fruitbasket.orange.module.sys.pojo.query.DictUpdateQuery;
import cn.fruitbasket.orange.module.sys.pojo.vo.DictComponentVO;
import cn.fruitbasket.orange.module.sys.pojo.vo.DictVO;
import cn.fruitbasket.orange.module.sys.repository.DictRep;
import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Set;

import static cn.fruitbasket.orange.module.common.entity.BaseDO.DEFAULT_SORT_VALUE;
import static cn.fruitbasket.orange.util.CustomBeanUtils.IGNORE_NULL_COPY_OPTION;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@Slf4j
@Service
public class DictService {

    private final DictRep dictRep;

    /**
     * 分页查询
     *
     * @param query -
     * @return 一页数据
     */
    public PageVO<DictVO> listPageDict(DictPageableQuery query) {
        Pageable pageable = PageRequest.of(query.getPageNumber(), query.getPageSize());
        Page<SysDict> page = dictRep.findAllByDictNameContainingOrAndDictShowNameContainingOrderByGmtCreateDesc(
                query.getDictName(), query.getDictShowName(), pageable);
        return PageVO.of(page, DictVO::of);
    }

    /**
     * 新增一个字典
     *
     * @param query -
     * @return 完整字典信息
     */
    @Transactional
    public DictVO saveDict(DictAddQuery query) {
        SysDict dict = new SysDict().setDictName(query.getDictName());
        if (dictRep.count(Example.of(dict)) > 0) throw new ShowToClientException("字典已存在");
        BeanUtil.copyProperties(query, dict, IGNORE_NULL_COPY_OPTION);
        if (!CollectionUtils.isEmpty(query.getDictOptions())) {
            dict.setDictOptions(query.getDictOptions().stream()
                    .map(op -> {
                        SysDictOption option = BeanUtil.copyProperties(op, SysDictOption.class);
                        return isNull(option.getSortValue()) ? option.setSortValue(DEFAULT_SORT_VALUE) : option;
                    })
                    .collect(toList()));
        }
        dictRep.save(dict);
        return DictVO.of(dict);
    }

    /**
     * 修改配置
     *
     * @param query 配置信息
     * @return 修改后的配置信息
     */
    @Transactional
    public DictVO updateDict(DictUpdateQuery query) {
        SysDict dict = dictRep.findById(query.getId())
                .orElseThrow(() -> new ShowToClientException("字典信息不存在"));
        BeanUtil.copyProperties(query, dict, IGNORE_NULL_COPY_OPTION);
        if (!CollectionUtils.isEmpty(query.getDictOptions())) {
            dict.setDictOptions(query.getDictOptions().stream()
                    .map(op -> {
                        SysDictOption option = BeanUtil.copyProperties(op, SysDictOption.class);
                        return isNull(option.getSortValue()) ? option.setSortValue(DEFAULT_SORT_VALUE) : option;
                    })
                    .collect(toList()));
        }
        dictRep.save(dict);
        return DictVO.of(dict);
    }

    /**
     * 根据 ID 删除
     *
     * @param ids -
     * @return 删除数量
     */
    @Transactional
    public long deleteDictIdIn(Set<Integer> ids) {
        return dictRep.deleteByIdIn(ids);
    }

    /**
     * 根据字典名称查找字典信息
     *
     * @param dictName -
     * @return -
     */
    public DictComponentVO getDict(String dictName) {
        SysDict dict = dictRep.findByDictName(dictName).orElse(null);
        return isNull(dict) ? null : DictComponentVO.of(dict);
    }

    public DictService(DictRep dictRep) {
        this.dictRep = dictRep;
    }
}
