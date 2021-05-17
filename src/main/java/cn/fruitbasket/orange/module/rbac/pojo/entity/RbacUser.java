package cn.fruitbasket.orange.module.rbac.pojo.entity;

import cn.fruitbasket.orange.dict.SexEnum;
import cn.fruitbasket.orange.module.common.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeleteAll;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.List;

import static cn.fruitbasket.orange.module.common.entity.BaseDO.NOT_DELETE_CONDITION;
import static cn.fruitbasket.orange.module.rbac.pojo.entity.RbacRole.TABLE_NAME;
import static javax.persistence.CascadeType.ALL;

/**
 * 用户
 *
 * @author LiuBing
 * @date 2020/12/15
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(exclude = "roles")
@Entity
@Where(clause = NOT_DELETE_CONDITION)
@SQLDelete(sql = "UPDATE " + TABLE_NAME + " SET deleted = true WHERE id = ?")
@SQLDeleteAll(sql = "UPDATE " + TABLE_NAME + " SET deleted = true WHERE id = ?")
public class RbacUser extends BaseDO<RbacUser> {

    static final String TABLE_NAME = "rbac_user";

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
    @Column(length = 50, nullable = false)
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
    @ManyToMany(cascade = ALL)
    private List<RbacRole> roles;
}
