package com.example.spring.boot.zhaoyun.module;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author LiuBing
 * @date 2020/12/18
 */
@Controller
@RequestMapping("/index")
public class PagesController {
	@RequestMapping("/layui")
	public String index() {
		return "index";
	}
}
