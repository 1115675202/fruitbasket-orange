package com.fruitbasket.orange.module.rbac.pojo.query;

import com.fruitbasket.orange.dict.SexEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * 用户修改
 *
 * @author LiuBing
 * @date 2021/4/21
 */
@Data
@Accessors(chain = true)
public class UserUpdateQuery {

    /**
     * 用户 ID
     */
    @NotNull
    private Integer id;

    /**
     * 账号
     */
    @Size(min = 8, max = 20, message = "账号[username]：长度为 8 ~ 20")
    private String username;

    /**
     * 密码
     */
    @Size(min = 8, max = 20, message = "密码[password]：长度为 8 ~ 20")
    private String password;

    /**
     * 性别
     */
    private SexEnum sex;

    /**
     * 真实姓名
     */
    @Size(min = 1, max = 50, message = "真实姓名[realName]：长度为 1 ~ 50")
    private String realName;

    /**
     * 身份证号
     */
    @Size(min = 1, max = 50, message = "身份证号[idCardNo]：长度为 1 ~ 50")
    private String idCardNo;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 头像链接
     */
    @Size(min = 1, max = 100, message = "身份证号[idCardNo]：长度为 1 ~ 100")
    private String avatarLink;
}
