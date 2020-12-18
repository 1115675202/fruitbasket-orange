package com.example.spring.boot.zhaoyun.module.sys.controller;

import com.example.spring.boot.zhaoyun.module.sys.api.SysApi;
import com.example.spring.boot.zhaoyun.module.sys.api.ivo.ISysConfigVO;
import com.example.spring.boot.zhaoyun.module.sys.service.SysConfigService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@RestController
public class SysController implements SysApi {

	@Autowired
	private SysConfigService sysConfigS;

	@Override
	public List<ISysConfigVO> listConfigs() {
		return sysConfigS.listAllSysConfig();
	}
}
