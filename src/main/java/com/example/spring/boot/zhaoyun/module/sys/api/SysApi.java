package com.example.spring.boot.zhaoyun.module.sys.api;

import com.example.spring.boot.zhaoyun.module.sys.api.ivo.ISysConfigVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@Api(tags = {"系统"})
@RequestMapping("sys")
public interface SysApi {


	@ApiOperation(value = "所有配置")
	@GetMapping("configs")
	List<ISysConfigVO> listConfigs();
}
