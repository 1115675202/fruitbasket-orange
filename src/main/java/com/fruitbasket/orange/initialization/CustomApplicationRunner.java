package com.fruitbasket.orange.initialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fruitbasket.orange.dict.SexEnum;
import com.fruitbasket.orange.module.core.pojo.entity.Permission;
import com.fruitbasket.orange.module.core.pojo.entity.Role;
import com.fruitbasket.orange.module.core.pojo.entity.User;
import com.fruitbasket.orange.module.core.pojo.entity.UserAccount;
import com.fruitbasket.orange.module.core.repository.UserRep;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        UserAccount userAccount = objectMapper.readValue("{\n" +
                "  \"identifier\": \"admin\",\n" +
                "  \"credential\": \"admin\"\n" +
                "}", UserAccount.class);
        User user = objectMapper.readValue("{\n" +
                "  \"sex\": \"WOMAN\",\n" +
                "  \"realName\": \"admin\",\n" +
                "  \"idCardNo\": \"430321000000000000\",\n" +
                "  \"birthday\": \"2021-04-19\",\n" +
                "  \"avatarLink\": \"/\"\n" +
                "}", User.class);
        userAccount.setUser(user);
        user.setRoles(roles());
        user.getRoles().forEach(role -> role.setUsers(Arrays.asList(user)));
        user.setUserAccounts(Arrays.asList(userAccount));
        userRep.save(user);
    }

    private List<Role> roles() throws JsonProcessingException {
        Role role = objectMapper.readValue("{\n" +
                "  \"roleName\": \"\",\n" +
                "  \"roleShowName\": \"\",\n" +
                "  \"sortValue\": 0,\n" +
                "  \"description\": \"\",\n" +
                "  \"deleted\": false,\n" +
                "  \"gmtCreate\": \"2021-04-19 18:37:06\",\n" +
                "  \"gmtModified\": \"2021-04-19 18:37:06\"\n" +
                "}", Role.class);
        List<Permission> permissions = permissions();
        role.setPermissions(permissions);
        List<Role> ret = Arrays.asList(role);
        permissions.forEach(permission -> permission.setRoles(ret));
        return ret;
    }

    private List<Permission> permissions() throws JsonProcessingException {
        List<Permission> ret = new ArrayList<>();
        ret.add(objectMapper.readValue("{\n" +
                "        \"pid\": 0,\n" +
                "        \"permissionName\": \"SYS\",\n" +
                "        \"permissionShowName\": \"系统\",\n" +
                "        \"permissionLink\": \"/\",\n" +
                "        \"permissionType\": \"MENU\",\n" +
                "        \"breadcrumbs\": \"\",\n" +
                "        \"permissionLevel\": 1\n" +
                "}", Permission.class));

        ret.add(objectMapper.readValue("{\n" +
                "        \"pid\": 1,\n" +
                "        \"permissionName\": \"USER\",\n" +
                "        \"permissionShowName\": \"用户\",\n" +
                "        \"permissionLink\": \"/user\",\n" +
                "        \"permissionType\": \"MENU\",\n" +
                "        \"breadcrumbs\": \"/0\",\n" +
                "        \"permissionLevel\": 2\n" +
                "}", Permission.class));

        ret.add(objectMapper.readValue("{\n" +
                "        \"pid\": 1,\n" +
                "        \"permissionName\": \"ROLE\",\n" +
                "        \"permissionShowName\": \"角色\",\n" +
                "        \"permissionLink\": \"/role\",\n" +
                "        \"permissionType\": \"MENU\",\n" +
                "        \"breadcrumbs\": \"/0\",\n" +
                "        \"permissionLevel\": 2\n" +
                "}", Permission.class));

        ret.add(objectMapper.readValue("{\n" +
                "        \"pid\": 1,\n" +
                "        \"permissionName\": \"PERMISSION\",\n" +
                "        \"permissionShowName\": \"权限\",\n" +
                "        \"permissionLink\": \"/permission\",\n" +
                "        \"permissionType\": \"MENU\",\n" +
                "        \"breadcrumbs\": \"/0\",\n" +
                "        \"permissionLevel\": 2\n" +
                "}", Permission.class));
        return ret;
    }

    public CustomApplicationRunner(ObjectMapper objectMapper, UserRep userRep) {
        this.objectMapper = objectMapper;
        this.userRep = userRep;
    }
}
