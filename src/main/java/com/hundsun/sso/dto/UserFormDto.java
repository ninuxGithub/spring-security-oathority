package com.hundsun.sso.dto;

import com.hundsun.sso.domain.Privilege;
import com.hundsun.sso.domain.User;
import com.hundsun.sso.utils.PasswordHandler;

public class UserFormDto extends UserDto {

	private static final long serialVersionUID = 1982181101294470051L;

	private String password;

	public UserFormDto() {
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Privilege[] getAllPrivileges() {
		return new Privilege[] { Privilege.MOBILE, Privilege.UNITY };
	}

	public User newUser() {
		User user = new User();
		user.setUsername(getUsername());
		user.setPhone(getPhone());
		user.setPassword(PasswordHandler.md5(getPassword()));
		user.setEmail(getEmail());
		user.getPriviliges().addAll(getPrivileges());
		return user;

	}

}
