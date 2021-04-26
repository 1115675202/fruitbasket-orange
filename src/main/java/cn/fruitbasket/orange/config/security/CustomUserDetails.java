package cn.fruitbasket.orange.config.security;

import cn.fruitbasket.orange.util.MapUtils;
import cn.fruitbasket.orange.module.rbac.pojo.entity.RbacRole;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static java.util.Collections.emptySet;

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
    private Collection<GrantedAuthority> authorities;

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
     * 加载角色，用来鉴权
     *
     * @param roles 角色
     * @return this
     */
    public CustomUserDetails loadAuthoritiesBy(List<RbacRole> roles) {
        if (roles.isEmpty()) {
            this.authorities = emptySet();
            return this;
        }
        this.authorities = new HashSet<>(MapUtils.capacity(roles.size()));
        roles.stream().map(RbacRole::getRoleName)
                .forEach(roleName -> this.authorities.add(() -> roleName));
        return this;
    }
}
