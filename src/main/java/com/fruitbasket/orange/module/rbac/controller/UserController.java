package com.fruitbasket.orange.module.rbac.controller;

import com.fruitbasket.orange.config.security.CustomUserDetails;
import com.fruitbasket.orange.module.common.vo.PageVO;
import com.fruitbasket.orange.module.rbac.pojo.query.UserAddQuery;
import com.fruitbasket.orange.module.rbac.pojo.query.UserPageableQuery;
import com.fruitbasket.orange.module.rbac.pojo.query.UserUpdateQuery;
import com.fruitbasket.orange.module.rbac.pojo.vo.MenuTreeNodeVO;
import com.fruitbasket.orange.module.rbac.pojo.vo.UserPageVO;
import com.fruitbasket.orange.module.rbac.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

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

    /**
     * 添加一个用户
     *
     * @param query 用户信息
     * @return 生成的用户信息
     */
    @PostMapping
    public UserPageVO addUser(@RequestBody @Valid UserAddQuery query) {
        return userService.save(query);
    }

    /**
     * 根据权限 ID 删除多个用户
     *
     * @param ids -
     * @return 删除数量
     */
    @DeleteMapping("multi")
    public long deleteUsersIdIn(@RequestBody @Valid @NotEmpty(message = "用户ID：不能为空") Set<Integer> ids) {
        return userService.deleteUsersIdIn(ids);
    }

    /**
     * 修改用户信息
     *
     * @param query 用户信息
     * @return 修改后的用户信息
     */
    @PutMapping
    public UserPageVO updateUser(@RequestBody @Valid UserUpdateQuery query) {
        return userService.updateUser(query);
    }

    /**
     * @return 一页信息
     */
    @GetMapping("multi")
    public PageVO<UserPageVO> listPageUsers(@Valid UserPageableQuery query) {
        return userService.listPageUsers(query);
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
