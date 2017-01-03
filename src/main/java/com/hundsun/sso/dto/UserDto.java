package com.hundsun.sso.dto;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.hundsun.sso.domain.Privilege;
import com.hundsun.sso.domain.User;

public class UserDto implements Serializable {

	private static final long serialVersionUID = -1824299969397259956L;

	private String guid;
	private String username;
	private String phone;
	private String email;
	private String createTime;
	private List<Privilege> privileges = new ArrayList<>();

	public UserDto() {
	}

	/**
	 * 以user 入参 构造 userDto
	 * 
	 * @param user
	 */
	public UserDto(User user) {
		this.guid = user.getGuid();
		this.username = user.getUsername();
		this.phone = user.getPhone();
		this.email = user.getEmail();
		this.privileges = user.getPriviliges();
		this.createTime = user.getCreateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	/**
	 * from User to UserDto
	 * @param users
	 * @return
	 */
	public static List<UserDto> toDtos(List<User> users) {
		List<UserDto> dtos = new ArrayList<>(users.size());
		for (User user : users) {
			dtos.add(new UserDto(user));
		}
		return dtos;
	}

}
