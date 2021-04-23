package com.fruitbasket.orange.module.rbac.controller;

import com.fruitbasket.orange.config.security.CustomUserDetails;
import com.fruitbasket.orange.module.rbac.pojo.vo.MenuTreeNodeVO;
import com.fruitbasket.orange.module.rbac.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@RequestMapping("user")
@RestController
public class UserController {

    private final UserService userService;

    /**
     * @return 登录信息
     */
    @GetMapping("login/info")
    public CustomUserDetails login() {
        return userService.getLoginInfo();
    }

    /**
     * 界面左边的菜单接口
     *
     * @return 包含菜单、api等权限信息
     */
    @GetMapping("menu/trees")
    public List<MenuTreeNodeVO> getMenuTrees() {
        return userService.getMenuTrees();
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
