package cn.fruitbasket.orange.initialization;

import cn.fruitbasket.orange.module.rbac.pojo.entity.RbacPermission;
import cn.fruitbasket.orange.module.rbac.pojo.entity.RbacRole;
import cn.fruitbasket.orange.module.rbac.pojo.entity.RbacUser;
import cn.fruitbasket.orange.module.rbac.repository.UserRep;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author LiuBing
 * @date 2021/4/19
 */
@Component
public class TestDataInit extends AbstractDataInit {

    private final UserRep userRep;

    @Override
    protected boolean support() {
        return userRep.count() == 0;
    }

    @Override
    protected String jsonFilePath() {
        return "/initialization/data.test.json";
    }

    @Override
    protected void finalRun(ApplicationArguments args) {
        List<RbacUser> users = super.getObjects("RbacUser", RbacUser.class);
        List<RbacRole> roles = super.getObjects("RbacRole", RbacRole.class);
        List<RbacPermission> permissions = super.getObjects("RbacPermission", RbacPermission.class);
        users.forEach(user -> user.setRoles(roles));
        permissions.forEach(permission -> permission.setRoles(roles));
        roles.forEach(role -> {
            role.setPermissions(permissions);
            role.setUsers(users);
        });
        userRep.saveAll(users);
    }

    public TestDataInit(UserRep userRep) {
        super();
        this.userRep = userRep;
    }
}
