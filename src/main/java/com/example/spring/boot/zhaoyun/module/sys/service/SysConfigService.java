package com.example.spring.boot.zhaoyun.module.sys.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.example.spring.boot.zhaoyun.constant.ConfigConsts.CONFIG_CACHE_OPEN;

/**
 * 系统配置
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Service
public class SysConfigService {

	/**
	 * 游戏配置缓存
	 */
	private static final Map<String, String> configCache;

	/**
	 * 加载游戏全局配置到缓存
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
}
