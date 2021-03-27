package com.fruitbasket.orange.module.sys.service;

import com.fruitbasket.orange.module.sys.pojo.entity.SysConfig;
import com.fruitbasket.orange.module.sys.pojo.vo.SysConfigVO;
import com.fruitbasket.orange.module.sys.repository.SysConfigRep;
import com.fruitbasket.orange.util.BeanCopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static com.fruitbasket.orange.constant.ConfigConstants.CONFIG_CACHE_OPEN;

/**
 * 系统配置
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Slf4j
@Service
public class SysConfigService {

	private SysConfigRep sysConfigR;

	/**
	 * 配置缓存
	 */
	private static final Map<String, String> configCache;

	/**
	 * 所有配置列表
	 *
	 * @return
	 */
	public List<SysConfigVO> listAllSysConfig() {
		List<SysConfig> sysConfigList = sysConfigR.findAllById(new ArrayList<Integer>(){{add(1);}});
		if (sysConfigList.isEmpty()) {
			return Collections.emptyList();
		}

		return BeanCopyUtils
				.instantiateCopy(sysConfigList, SysConfigVO.class)
				.collect(Collectors.toList());
	}

	/**
	 * 加载配置到缓存
	 */
	public void reloadConfig() {
		if (!CONFIG_CACHE_OPEN) {
			return;
		}
	}

	/**
	 * 新增/更新配置
	 *
	 * @param key
	 * @param value
	 */
	public void saveConfig(String key, String value) {
	}

	public String stringConfigOf(String key) {
		return configValue(key);
	}

	public int intConfigOf(String key) {
		return Integer.parseInt(configValue(key));
	}

	public Integer integerConfigOf(String key) {
		return Integer.valueOf(configValue(key));
	}

	public long longConfigOf(String key) {
		return Long.parseLong(configValue(key));
	}

	public Long longObjConfigOf(String key) {
		return Long.valueOf(configValue(key));
	}

	public BigDecimal floatConfigOf(String key) {
		return new BigDecimal(configValue(key));
	}

	/**
	 * 获取配置
	 *
	 * @param key
	 * @return
	 */
	private String configValue(String key) {
		if (CONFIG_CACHE_OPEN) {
			return configCache.get(key);
		}

		return null;
	}

	static {
		configCache = new ConcurrentHashMap<>();
	}

	public SysConfigService(SysConfigRep sysConfigR) {
		this.sysConfigR = sysConfigR;
	}
}
