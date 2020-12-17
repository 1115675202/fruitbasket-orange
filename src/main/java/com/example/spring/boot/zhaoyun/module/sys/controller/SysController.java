package com.example.spring.boot.zhaoyun.module.sys.controller;

import com.example.spring.boot.zhaoyun.module.sys.api.am.ISysConfigAM;
import com.example.spring.boot.zhaoyun.module.sys.api.SysApi;
import com.example.spring.boot.zhaoyun.module.sys.pojo.entity.SysConfig;
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
	public ISysConfigAM listConfigs() {
		return new SysConfig();
	}
}
