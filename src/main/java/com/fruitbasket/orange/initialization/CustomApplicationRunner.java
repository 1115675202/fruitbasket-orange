package com.fruitbasket.orange.initialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fruitbasket.orange.module.rbac.pojo.entity.RbacPermission;
import com.fruitbasket.orange.module.rbac.pojo.entity.RbacRole;
import com.fruitbasket.orange.module.rbac.pojo.entity.RbacUser;
import com.fruitbasket.orange.module.rbac.repository.UserRep;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author LiuBing
 * @date 2021/4/19
 */
@Component
public class CustomApplicationRunner implements ApplicationRunner {

    private final ObjectMapper objectMapper;

    private final UserRep userRep;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initDefaultTestData();
    }

    private void initDefaultTestData() throws JsonProcessingException {
        if (userRep.count() > 0) return;
        RbacUser user = objectMapper.readValue("{\n" +
                "  \"username\": \"admin\",\n" +
                "  \"password\": \"admin\",\n" +
                "  \"sex\": \"WOMAN\",\n" +
                "  \"realName\": \"admin\",\n" +
                "  \"idCardNo\": \"430321000000000000\",\n" +
                "  \"birthday\": \"2021-04-19\",\n" +
                "  \"avatarLink\": \"/\"\n" +
                "}", RbacUser.class);
        user.setRoles(roles());
        user.getRoles().forEach(role -> role.setUsers(Collections.singleton(user)));
        userRep.save(user);
    }

    private Set<RbacRole> roles() throws JsonProcessingException {
        RbacRole role = objectMapper.readValue("{\n" +
                "  \"roleName\": \"ADMIN\",\n" +
                "  \"roleShowName\": \"管理员\",\n" +
                "  \"sortValue\": 0,\n" +
                "  \"description\": \"\",\n" +
                "  \"deleted\": false,\n" +
                "  \"gmtCreate\": \"2021-04-19 18:37:06\",\n" +
                "  \"gmtModified\": \"2021-04-19 18:37:06\"\n" +
                "}", RbacRole.class);
        Set<RbacPermission> permissions = permissions();
        role.setPermissions(permissions);
        Set<RbacRole> ret = Collections.singleton(role);
        permissions.forEach(permission -> permission.setRoles(ret));
        return ret;
    }

    private Set<RbacPermission> permissions() throws JsonProcessingException {
        Set<RbacPermission> ret = new HashSet<>();
        ret.add(objectMapper.readValue("{\n" +
                "        \"pid\": 0,\n" +
                "        \"permissionName\": \"SYS\",\n" +
                "        \"permissionShowName\": \"系统\",\n" +
                "        \"permissionLink\": \"/\",\n" +
                "        \"permissionType\": \"MENU\",\n" +
                "        \"breadcrumbs\": \"\",\n" +
                "        \"sortValue\": \"0\",\n" +
                "        \"permissionLevel\": 1\n" +
                "}", RbacPermission.class));

        ret.add(objectMapper.readValue("{\n" +
                "        \"pid\": 1,\n" +
                "        \"permissionName\": \"USER\",\n" +
                "        \"permissionShowName\": \"用户\",\n" +
                "        \"permissionLink\": \"/user\",\n" +
                "        \"permissionType\": \"MENU\",\n" +
                "        \"breadcrumbs\": \"/0\",\n" +
                "        \"sortValue\": \"0\",\n" +
                "        \"permissionLevel\": 2\n" +
                "}", RbacPermission.class));

        ret.add(objectMapper.readValue("{\n" +
                "        \"pid\": 1,\n" +
                "        \"permissionName\": \"ROLE\",\n" +
                "        \"permissionShowName\": \"角色\",\n" +
                "        \"permissionLink\": \"/role\",\n" +
                "        \"permissionType\": \"MENU\",\n" +
                "        \"breadcrumbs\": \"/0\",\n" +
                "        \"sortValue\": \"0\",\n" +
                "        \"permissionLevel\": 2\n" +
                "}", RbacPermission.class));

        ret.add(objectMapper.readValue("{\n" +
                "        \"pid\": 1,\n" +
                "        \"permissionName\": \"PERMISSION\",\n" +
                "        \"permissionShowName\": \"权限\",\n" +
                "        \"permissionLink\": \"/permission\",\n" +
                "        \"permissionType\": \"MENU\",\n" +
                "        \"breadcrumbs\": \"/0\",\n" +
                "        \"sortValue\": \"0\",\n" +
                "        \"permissionLevel\": 2\n" +
                "}", RbacPermission.class));
        return ret;
    }

    public CustomApplicationRunner(ObjectMapper objectMapper, UserRep userRep) {
        this.objectMapper = objectMapper;
        this.userRep = userRep;
    }
}
