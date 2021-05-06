package cn.fruitbasket.orange.module.sys.repository;

import cn.fruitbasket.orange.module.sys.pojo.entity.SysDict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

/**
 * 字典
 *
 * @author LiuBing
 * @date 2020/12/15
 */
public interface DictRep extends JpaRepository<SysDict, Integer> {

    /**
     * 分页查询配置
     *
     * @param dictName     字典名称
     * @param dictShowName 字典显示名称
     * @param pageable     分页参数
     * @return 一页数据
     */
    Page<SysDict> findAllByDictNameContainingOrAndDictShowNameContainingOrderByGmtCreateDesc
    (String dictName, String dictShowName, Pageable pageable);

    /**
     * 根据 ID 删除
     *
     * @param ids ID 列表
     * @return 删除数量
     */
    long deleteByIdIn(Set<Integer> ids);

    /**
     * 根据字典名称查找字典信息
     *
     * @param dictName -
     * @return -
     */
    Optional<SysDict> findByDictName(String dictName);
}
