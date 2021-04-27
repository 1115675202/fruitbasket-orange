package cn.fruitbasket.orange.module.sys.service;

import cn.fruitbasket.orange.config.exception.ShowToClientException;
import cn.fruitbasket.orange.module.common.vo.PageVO;
import cn.fruitbasket.orange.module.sys.pojo.entity.SysConfig;
import cn.fruitbasket.orange.module.sys.pojo.query.ConfigAddQuery;
import cn.fruitbasket.orange.module.sys.pojo.query.ConfigPageableQuery;
import cn.fruitbasket.orange.module.sys.pojo.query.ConfigUpdateQuery;
import cn.fruitbasket.orange.module.sys.pojo.vo.ConfigVO;
import cn.fruitbasket.orange.module.sys.repository.SysConfigRep;
import cn.fruitbasket.orange.util.CustomBeanUtils;
import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Set;

import static cn.fruitbasket.orange.util.CustomBeanUtils.IGNORE_NULL_COPY_OPTION;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

/**
 * 系统配置
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Slf4j
@Service
public class ConfigService {

	private final SysConfigRep sysConfigRep;

	/**
	 * 分页查询配置
	 *
	 * @param query -
	 * @return 一页配置
	 */
	public PageVO<ConfigVO> listPageConfig(ConfigPageableQuery query) {
		Pageable pageable = PageRequest.of(query.getPageNumber(), query.getPageSize());
		Page<SysConfig> page = sysConfigRep.findAllByConfigKey(query.getConfigKey(), pageable);
		return PageVO.of(page, ConfigVO::of);
	}

	/**
	 * 新增一个配置
	 *
	 * @param query -
	 * @return 完整配置信息
	 */
	@Transactional
	public ConfigVO saveConfig(ConfigAddQuery query) {
		SysConfig config = new SysConfig().setConfigKey(query.getConfigKey());
		if (sysConfigRep.count(Example.of(config)) > 0) throw new ShowToClientException("配置键已存在");
		BeanUtil.copyProperties(query, config, IGNORE_NULL_COPY_OPTION);
		sysConfigRep.save(config);
		return ConfigVO.of(config);
	}

	/**
	 * 修改配置
	 *
	 * @param query 配置信息
	 * @return 修改后的配置信息
	 */
	@Transactional
	public ConfigVO updateConfig(ConfigUpdateQuery query) {
		SysConfig config = sysConfigRep.findById(query.getId())
				.orElseThrow(() -> new ShowToClientException("配置信息不存在"));
		BeanUtil.copyProperties(query, config, CustomBeanUtils.IGNORE_NULL_COPY_OPTION);
		sysConfigRep.save(config);
		return ConfigVO.of(config);
	}

	/**
	 * 根据 ID 删除
	 *
	 * @param ids -
	 * @return 删除数量
	 */
	@Transactional
	public long deleteConfigIdIn(Set<Integer> ids) {
		return sysConfigRep.deleteByIdIn(ids);
	}

	/**
	 * 根据配置键获取配置值
	 *
	 * @param configKey    键
	 * @param defaultValue 默认值
	 * @param <R>          返回类型
	 * @return - null-不支持的类型
	 */
	@SuppressWarnings("unchecked")
	public <R> R configValueOrDefault(String configKey, R defaultValue) {
		requireNonNull(defaultValue);
		String configValue = configValue(configKey);
		if (isNull(configValue)) return defaultValue;
		else if (defaultValue instanceof String) return (R) configValue;
		else if (defaultValue instanceof Integer) return (R) Integer.valueOf(configValue);
		else if (defaultValue instanceof Long) return (R) Long.valueOf(configValue);
		else if (defaultValue instanceof BigDecimal) return (R) new BigDecimal(configValue);
		else return null;
	}

	private String configValue(String configKey) {
		SysConfig config = sysConfigRep.findByConfigKey(configKey).orElse(null);
		return isNull(config) ? null : config.getConfigValue();
	}

	public ConfigService(SysConfigRep sysConfigRep) {
		this.sysConfigRep = sysConfigRep;
	}
}
