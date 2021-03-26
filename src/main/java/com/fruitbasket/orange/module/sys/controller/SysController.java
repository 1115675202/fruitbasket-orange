package com.fruitbasket.orange.module.sys.controller;

import com.fruitbasket.orange.module.sys.api.SysApi;
import com.fruitbasket.orange.module.sys.api.ivo.ISysConfigVO;
import com.fruitbasket.orange.module.sys.service.SysConfigService;
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
