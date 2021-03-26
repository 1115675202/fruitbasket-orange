package com.fruitbasket.orange.module;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author LiuBing
 * @date 2020/12/18
 */
@Controller
@RequestMapping("/show")
public class PagesController {
	@RequestMapping("/show")
	public String index(Model model) {
		model.addAttribute("uid","123456789");
		model.addAttribute("name","Jerry");
		return "show";
	}
}
