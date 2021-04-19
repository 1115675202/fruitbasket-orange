package com.fruitbasket.orange.module.core.pojo.entity;

import com.fruitbasket.orange.module.common.entity.BaseDO;
import com.fruitbasket.orange.dict.SexEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.*;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/15
 */
@Data
@Accessors(chain = true)
@Entity
@SQLDelete(sql = "UPDATE user SET deleted = 1 WHERE id = ?")
public class User extends BaseDO {

    /**
     * 性别
     */
    @Column(nullable = false)
    private SexEnum sex;

    /**
     * 真实姓名
     */
    @Column(nullable = false, unique = true, length = 50)
    private String realName;

    /**
     * 身份证号
     */
    @Column(nullable = false, unique = true, length = 50)
    private String idCardNo;

    /**
     * 生日
     */
    @Column(nullable = false)
    private LocalDate birthday;

    /**
     * 头像链接
     */
    @Column(nullable = false, length = 100)
    private String avatarLink = "";

    /**
     * 关联的账号列表，如本地账号、微博账号
     */
    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<UserAccount> userAccounts;

    /**
     * 权限列表
     */
    @ManyToMany(mappedBy = "users", cascade = ALL)
    private List<Role> roles;
}
