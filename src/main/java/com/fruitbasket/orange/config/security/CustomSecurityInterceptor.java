package com.fruitbasket.orange.config.security;

import com.fruitbasket.orange.module.rbac.service.RoleService;
import com.fruitbasket.orange.module.rbac.service.UserService;
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
 *
 * @author LiuBing
 * @date 2021/4/21
 */
@Component
public class CustomSecurityInterceptor extends OncePerRequestFilter {

    private static final String ACCESS_DENIED_MSG = "access denied";

    private final UserService userService;

    private final RoleService roleService;

    /**
     * 如果接口有指定角色，则只有这些角色可访问
     * 如果接口未指定角色，则都可以访问
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        FilterInvocation filterInvocation = new FilterInvocation(request, response, filterChain);
        CustomUserDetails authentication = userService.getLoginInfo();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        Set<String> roles = roleService.listBeAuthorizedRoleNamesOf(filterInvocation.getRequestUrl());
        if (!roles.isEmpty()) {
            boolean isAnyBeAuthorizedRole = authorities.stream().map(GrantedAuthority::getAuthority).anyMatch(roles::contains);
            if (!isAnyBeAuthorizedRole) {
                throw new AccessDeniedException(ACCESS_DENIED_MSG);
            }
        }

        filterChain.doFilter(request, response);
    }

    public CustomSecurityInterceptor(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
}
