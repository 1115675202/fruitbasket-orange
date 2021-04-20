package com.fruitbasket.orange.config.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

import static com.fruitbasket.orange.config.security.CustomFilterInvocationSecurityMetadataSource.NO_USER_ALLOW;
import static java.util.stream.Collectors.toSet;
import static org.springframework.util.CollectionUtils.isEmpty;

/**
 * @author LiuBing
 * @date 2021/4/20
 */
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {

    private static final String ACCESS_DENIED_MSG = "access denied";

    /**
     * 判定是否拥有访问权限
     *
     * @param authentication    登陆缓存的鉴权信息，包含了用户角色信息
     * @param o                 可转为 FilterInvocation，包含请求信息
     * @param beAuthorizedRoles CustomFilterInvocationSecurityMetadataSource 的返回值
     * @throws AccessDeniedException               权限不足
     * @throws InsufficientAuthenticationException -
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> beAuthorizedRoles)
            throws AccessDeniedException, InsufficientAuthenticationException {

        if (NO_USER_ALLOW == beAuthorizedRoles) throw new AccessDeniedException(ACCESS_DENIED_MSG);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (isEmpty(authorities)) throw new AccessDeniedException(ACCESS_DENIED_MSG);

        Set<String> beAuthorizedRoleSet = beAuthorizedRoles.stream()
                .map(ConfigAttribute::getAttribute).collect(toSet());

        if (beAuthorizedRoleSet.contains("ROLE_NO_USER")) return;

        // 当前用户属于任一可访问角色则允许访问
        boolean accessAllow = authorities.stream()
                .anyMatch(authority -> beAuthorizedRoleSet.contains(authority.getAuthority()));
        if (!accessAllow) throw new AccessDeniedException(ACCESS_DENIED_MSG);
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
