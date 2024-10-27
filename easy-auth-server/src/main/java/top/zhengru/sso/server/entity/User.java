package top.zhengru.sso.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用户
 */
@TableName("sys_user")
public class User extends BaseEntity {
	/** 姓名 */
	private String name;

	/** 手机号 */
	private String phone;

	/** 卡号 */
	private String cardNo;

	/** 邮箱 */
	private String email;

	/** 用户名 */
	private String username;

	/** 密码 */
	private String password;

	/** 最后登录时间 */
	private LocalDateTime lastLoginTime;

	/** 登录总次数 */
	private Integer loginCount;

	/** 是否启用 */
	private Boolean isEnable;

	public User() {
	}

	public User(String name, String phone, String cardNo, String email, String username, String password, LocalDateTime lastLoginTime, Integer loginCount, Boolean isEnable) {
		this.name = name;
		this.phone = phone;
		this.cardNo = cardNo;
		this.email = email;
		this.username = username;
		this.password = password;
		this.lastLoginTime = lastLoginTime;
		this.loginCount = loginCount;
		this.isEnable = isEnable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(LocalDateTime lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Boolean getEnable() {
		return isEnable;
	}

	public void setEnable(Boolean enable) {
		isEnable = enable;
	}
}
