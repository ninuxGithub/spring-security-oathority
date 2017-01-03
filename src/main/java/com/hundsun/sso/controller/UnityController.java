package com.hundsun.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hundsun.sso.dto.UserJsonDto;
import com.hundsun.sso.service.UserService;

@Controller
@RequestMapping("/unity")
public class UnityController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard() {
		return "unity/dashboard";
	}

	@RequestMapping("user_info")
	@ResponseBody
	public UserJsonDto userInfo() {
		return userService.loadCurrentUserJsonDto();
	}

}