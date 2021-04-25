package com.fruitbasket.orange.module.rbac.pojo.entity;

import com.fruitbasket.orange.dict.SexEnum;
import com.fruitbasket.orange.module.common.entity.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/15
 */
@Data
@Accessors(chain = true)
@Entity
@SQLDelete(sql = "UPDATE rbac_user SET deleted = 1 WHERE id = ?")
public class RbacUser extends BaseDO {

    /**
     * 账号
     */
    @Column(length = 20, nullable = false, unique = true)
    private String username;

    /**
     * 密码
     */
    @Column(length = 20, nullable = false)
    private String password;

    /**
     * 性别
     */
    @Column
    private SexEnum sex;

    /**
     * 真实姓名
     */
    @Column(length = 50)
    private String realName;

    /**
     * 身份证号
     */
    @Column(unique = true, length = 50)
    private String idCardNo;

    /**
     * 生日
     */
    @Column
    private LocalDate birthday;

    /**
     * 头像链接
     */
    @Column(length = 100)
    private String avatarLink;

    /**
     * 权限列表
     */
    @ManyToMany(mappedBy = "users", cascade = ALL)
    private List<RbacRole> roles;
}
