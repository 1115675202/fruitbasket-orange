package cn.fruitbasket.orange.module.rbac.pojo.vo;

import cn.fruitbasket.orange.module.rbac.pojo.entity.RbacPermission;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 菜单下的按钮
 *
 * @author LiuBing
 * @date 2021/4/11
 */
@Data
@Accessors(chain = true)
public class MenuButtonNodeVO {

    /**
     * 按钮名称
     */
    private String buttonName;

    /**
     * 按钮显示名称
     */
    private String buttonShowName;

    public static MenuButtonNodeVO of(RbacPermission permission) {
        return new MenuButtonNodeVO()
                .setButtonName(permission.getPermissionName())
                .setButtonShowName(permission.getPermissionShowName());
    }
}
