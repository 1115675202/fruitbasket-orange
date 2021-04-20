package com.fruitbasket.orange.config.security;

import com.fruitbasket.orange.module.rbac.pojo.entity.RbacRole;
import com.fruitbasket.orange.module.rbac.service.RoleService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

import static java.util.Collections.singleton;
import static java.util.stream.Collectors.toSet;

/**
 * @author LiuBing
 * @date 2021/4/20
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final RoleService roleService;

    static final Collection<ConfigAttribute> NO_USER_ALLOW = singleton(new SecurityConfig("NO_USER_ALLOW"));

    /**
     * 该方法如果返回空集合或者 null， CustomAccessDecisionManager.decide(..) 将不会执行
     *
     * @param o 可转为 FilterInvocation，包含请求信息
     * @return 能访问该地址的角色
     * @throws IllegalArgumentException -
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        FilterInvocation invocation = (FilterInvocation) o;
        String requestUrl = invocation.getRequestUrl();

        List<RbacRole> roles = roleService.listBeAuthorizedRolesOf(requestUrl);
        return roles.isEmpty() ? NO_USER_ALLOW :
                roles.stream().map(RbacRole::getRoleName).map(SecurityConfig::new).collect(toSet());
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

    public CustomFilterInvocationSecurityMetadataSource(RoleService roleService) {
        this.roleService = roleService;
    }
}
