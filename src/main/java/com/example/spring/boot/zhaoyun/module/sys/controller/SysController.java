package com.example.spring.boot.zhaoyun.module.sys.controller;

import com.example.spring.boot.zhaoyun.module.sys.api.ISysConfig;
import com.example.spring.boot.zhaoyun.module.sys.api.SysApi;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@RestController
public class SysController implements SysApi {

	@Override
	public ISysConfig listConfigs() {
		return null;
	}
}
