package com.hundsun.sso.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class PasswordHandler {

	public static String md5(String password) {
		return new Md5PasswordEncoder().encodePassword(password, null);
	}

}
