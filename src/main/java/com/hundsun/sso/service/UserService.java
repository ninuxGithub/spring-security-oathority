package com.hundsun.sso.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.hundsun.sso.dto.UserFormDto;
import com.hundsun.sso.dto.UserJsonDto;
import com.hundsun.sso.dto.UserOverviewDto;

public interface UserService extends UserDetailsService {

	//default method 
	// public UserDetails loadUserByUsername(String username);

	UserJsonDto loadCurrentUserJsonDto();

	UserOverviewDto loadUserOverviewDto(UserOverviewDto overviewDto);

	boolean isExistedUsername(String username);

	String saveUser(UserFormDto formDto);
}
