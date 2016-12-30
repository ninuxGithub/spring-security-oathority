package com.hundsun.sso.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @function:
 * @spring-security-oathority :项目名称
 * @com.hundsun.sso.domain.User.java 类全路径
 * @2016年12月30日 下午3:31:32
 * @User
 *
 */
public class User extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -3170673291827584510L;

	/** 用户名称 **/
	private String username;

	/** 用户密码 **/
	private String password;

	/** 用户邮箱 **/
	private String email;

	/** 默认用户 **/
	private boolean defaultUser = false;

	/** 用户最后登录时间 **/
	private Date lastLoginTime;

	/** 用户权限 **/
	private List<Privilege> priviliges = new ArrayList<>();

	/** 用户联系方式 **/
	private String phone;

	public User() {
	}

	public User(String username, String password, String phone, String email) {
		this.username = username;
		this.password = password;
		this.phone = phone;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isDefaultUser() {
		return defaultUser;
	}

	public void setDefaultUser(boolean defaultUser) {
		this.defaultUser = defaultUser;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public List<Privilege> getPriviliges() {
		return priviliges;
	}

	public void setPriviliges(List<Privilege> priviliges) {
		this.priviliges = priviliges;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", email=" + email + ", defaultUser="
				+ defaultUser + ", lastLoginTime=" + lastLoginTime + ", priviliges=" + priviliges + "]";
	}

}
