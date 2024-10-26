package top.zhengru.sso.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 用户
 */
@TableName("sys_user")
public class User extends BaseEntity {
	
	/** 姓名 */
	private String name;
	/** 登录名 */
	private String account;
	/** 密码 */
	private String password;
	/** 登录总次数 */
	private Integer loginCount;
	/** 最后登录时间 */
	private Date lastLoginTime;
	/** 是否启用 */
	private Boolean isEnable;
}
