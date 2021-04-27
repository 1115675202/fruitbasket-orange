package cn.fruitbasket.orange.module.rbac.service;

import cn.fruitbasket.orange.module.rbac.pojo.query.UserBindRolesQuery;
import cn.fruitbasket.orange.module.rbac.pojo.query.UserPageableQuery;
import cn.fruitbasket.orange.module.rbac.pojo.query.UserUpdateQuery;
import cn.fruitbasket.orange.module.rbac.pojo.vo.PermissionTreeNodeVO;
import cn.fruitbasket.orange.module.rbac.pojo.vo.UserVO;
import cn.fruitbasket.orange.util.CustomBeanUtils;
import cn.hutool.core.bean.BeanUtil;
import cn.fruitbasket.orange.config.security.CustomUserDetails;
import cn.fruitbasket.orange.config.exception.ShowToClientException;
import cn.fruitbasket.orange.module.common.vo.PageVO;
import cn.fruitbasket.orange.module.rbac.pojo.entity.RbacPermission;
import cn.fruitbasket.orange.module.rbac.pojo.entity.RbacRole;
import cn.fruitbasket.orange.module.rbac.pojo.entity.RbacUser;
import cn.fruitbasket.orange.module.rbac.pojo.query.UserAddQuery;
import cn.fruitbasket.orange.module.rbac.repository.UserRep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.util.StringUtils.hasText;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@Slf4j
@Service
public class UserService {

    private final UserRep userRep;

    private final RoleService roleService;

    private final PermissionService permissionService;

    public UserService(UserRep userRep,
                       RoleService roleService,
                       PermissionService permissionService) {
        this.userRep = userRep;
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    /**
     * @param username 账户标识/账号
     * @return 账户及用户信息
     */
    public RbacUser getUserBy(String username) {
        return userRep.getRbacUserByUsernameIs(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found."));
    }

    /**
     * @return 菜单树形数据
     */
    public List<PermissionTreeNodeVO> getMenuTrees() {
        List<RbacPermission> permissions = permissionService.listPermissionsByUserId(getLoginInfo().getUserId());
        return PermissionTreeNodeVO.treeOf(permissions).getChildren();
    }

    /**
     * @return 用户登录信息
     */
    public CustomUserDetails getLoginInfo() {
        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 添加一个用户
     *
     * @param query 用户信息
     * @return 生成的用户信息
     */
    @Transactional
    public UserVO save(UserAddQuery query) {
        RbacUser user = new RbacUser().setUsername(query.getUsername());
        if (userRep.count(Example.of(user)) > 0) throw new ShowToClientException("账号[username]已被使用");
        BeanUtil.copyProperties(query, user);
        userRep.save(user);
        return UserVO.of(user);
    }

    /**
     * 根据 ID 删除
     *
     * @param ids -
     * @return 删除数量
     */
    @Transactional
    public long deleteUsersIdIn(Set<Integer> ids) {
        return userRep.deleteByIdIn(ids);
    }

    /**
     * 修改用户信息
     *
     * @param query 用户信息
     * @return 修改后的用户信息
     */
    @Transactional
    public UserVO updateUser(UserUpdateQuery query) {
        RbacUser user = userRep.findById(query.getId())
                .orElseThrow(() -> new ShowToClientException("用户信息不存在"));

        if (hasText(query.getUsername())
                && userRep.countByUsernameIs(query.getUsername()) > 0) {
            throw new ShowToClientException("用户名[username]已存在");
        }

        BeanUtil.copyProperties(query, user, CustomBeanUtils.IGNORE_NULL_COPY_OPTION);
        userRep.save(user);
        return UserVO.of(user);
    }

    /**
     * 分页查询
     *
     * @param query 分页以及检索参数
     * @return 分页信息
     */
    public PageVO<UserVO> listPageUsers(UserPageableQuery query) {
        Pageable pageable = PageRequest.of(query.getPageNumber(), query.getPageSize());
        Page<RbacUser> page = userRep.findAllByRealNameContains(query.getRealName(), pageable);
        return PageVO.of(page, UserVO::of);
    }

    /**
     * 绑定角色
     *
     * @param query 绑定信息
     */
    @Transactional
    public void bindingRoles(UserBindRolesQuery query) {
        RbacUser user = userRep.findById(query.getUserId())
                .orElseThrow(() -> new ShowToClientException("用户信息不存在"));

        Set<RbacRole> roles = new HashSet<>(roleService.listRolesOf(query.getRoleIds()));
        if (roles.size() < query.getRoleIds().size()) {
            roles.forEach(role ->
                    query.getRoleIds().remove(role.getId()));
            throw new ShowToClientException("角色信息不存在：roleIds" + query.getRoleIds());
        }

        user.setRoles(roles);
        userRep.save(user);
    }
}
