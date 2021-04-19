package com.fruitbasket.orange.config.security;

import com.fruitbasket.orange.module.core.pojo.entity.Permission;
import com.fruitbasket.orange.module.core.pojo.entity.Role;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * 自定义登陆后返回的用户详细信息
 * Spring安全3 @PreAuthorize(“hasRole(‘ROLE_XYZ’)”)
 * 与@PreAuthorize(“hasAuthority(‘ROLE_XYZ’)”与@PreAuthorize(“hasAuthority(‘ROLE_XYZ’)”)相同。
 *
 * @author LiuBing
 * @date 2021/4/17
 */
@Data
@Accessors(chain = true)
@Component
public class CustomUserDetails implements UserDetails {

    /**
     * 用户表主键
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 权限/角色
     */
    List<GrantedAuthority> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * 加载权限和角色，用来鉴权
     *
     * @param roles       角色
     * @param permissions 权限
     * @return this
     */
    public CustomUserDetails loadAuthoritiesBy(List<Role> roles, List<Permission> permissions) {
        authorities = new LinkedList<>();
        roles.forEach(role -> authorities.add(role::getRoleName));
        permissions.forEach(permission -> authorities.add(permission::getPermissionName));
        return this;
    }
}
