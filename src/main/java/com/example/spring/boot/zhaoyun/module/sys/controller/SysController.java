package com.example.spring.boot.zhaoyun.module.sys.controller;

import com.example.spring.boot.zhaoyun.module.sys.api.SysApi;
import com.example.spring.boot.zhaoyun.module.sys.api.am.ISysConfigVO;
import com.example.spring.boot.zhaoyun.module.sys.pojo.vo.SysConfigVO;
import com.example.spring.boot.zhaoyun.module.sys.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
