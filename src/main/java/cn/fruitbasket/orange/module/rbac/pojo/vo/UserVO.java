package cn.fruitbasket.orange.module.rbac.pojo.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.fruitbasket.orange.dict.SexEnum;
import cn.fruitbasket.orange.module.common.vo.BaseDataVO;
import cn.fruitbasket.orange.module.rbac.pojo.entity.RbacUser;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * 用户数据
 *
 * @author LiuBing
 * @date 2021/4/11
 */
@Data
@Accessors(chain = true)
public class UserVO extends BaseDataVO {

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别
     */
    private SexEnum sex;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 身份证号
     */
    private String idCardNo;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 头像链接
     */
    private String avatarLink;

    public static UserVO of(RbacUser user) {
        return BeanUtil.copyProperties(user, UserVO.class);
    }
}
