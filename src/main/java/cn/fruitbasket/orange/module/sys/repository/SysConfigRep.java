package cn.fruitbasket.orange.module.sys.repository;

import cn.fruitbasket.orange.module.sys.pojo.entity.SysConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

/**
 * 配置
 *
 * @author LiuBing
 * @date 2020/12/15
 */
public interface SysConfigRep extends JpaRepository<SysConfig, Integer> {

    /**
     * 分页查询配置
     *
     * @param configKey 配置键
     * @param pageable  分页参数
     * @return 一页数据
     */
    Page<SysConfig> findAllByConfigKey(String configKey, Pageable pageable);

    /**
     * 根据 ID 删除
     *
     * @param ids ID 列表
     * @return 删除数量
     */
    long deleteByIdIn(Set<Integer> ids);

    /**
     * 根据键查询配置信息
     *
     * @param configKey 键
     * @return -
     */
    Optional<SysConfig> findByConfigKey(String configKey);
}
