package cn.fruitbasket.orange.module.rbac.pojo.query;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 登陆
 *
 * @author LiuBing
 * @date 2020/12/9
 */
@Data
@Accessors(chain = true)
public class LoginQuery {

	/**
	 * 账号
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;
}
