package cn.fruitbasket.orange.config.security;

import cn.fruitbasket.orange.module.rbac.service.RoleService;
import cn.fruitbasket.orange.module.rbac.service.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

/**
 * 验证请求访问权限
 * OncePerRequestFilter 能保证只被调用一次
 * 直接实现 Filter 会被调用两次，虽然有其他解决方案，但是这种相对简单
 *
 * @author LiuBing
 * @date 2021/4/21
 */
@Component
public class CustomRoleSecurityFilter extends OncePerRequestFilter {

    private static final String ACCESS_DENIED_MSG = "access denied";

    private final UserService userService;

    private final RoleService roleService;

    /**
     * 如果接口有指定角色，则只有这些角色可访问
     * 如果接口未指定角色，则都可以访问
     *
     * @param request -
     * @param response -
     * @param filterChain -
     * @throws ServletException -
     * @throws IOException -
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        FilterInvocation filterInvocation = new FilterInvocation(request, response, filterChain);

        Set<String> roles = roleService.listBeAuthorizedRoleNamesOf(filterInvocation.getRequestUrl());
        if (!roles.isEmpty()) {
            CustomUserDetails authentication = userService.getLoginInfo();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            boolean isAnyBeAuthorizedRole = authorities.stream()
                    .map(GrantedAuthority::getAuthority).anyMatch(roles::contains);
            if (!isAnyBeAuthorizedRole) {
                throw new AccessDeniedException(ACCESS_DENIED_MSG);
            }
        }

        filterChain.doFilter(request, response);
    }

    public CustomRoleSecurityFilter(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
}
