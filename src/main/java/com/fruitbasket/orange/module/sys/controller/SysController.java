package com.fruitbasket.orange.module.sys.controller;

import com.fruitbasket.orange.module.sys.pojo.vo.SysConfigVO;
import com.fruitbasket.orange.module.sys.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 系统
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@RequestMapping("sys")
@RestController
public class SysController {

	private final SysConfigService sysConfigS;

	@GetMapping("configs")
	public List<SysConfigVO> listConfigs() {
		return sysConfigS.listAllSysConfig();
	}

	public SysController(SysConfigService sysConfigS) {
		this.sysConfigS = sysConfigS;
	}
}
