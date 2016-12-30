package com.hundsun.sso.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.hundsun.sso.utils.WebUtils;

/**
 * @function: 对字符集 IP的过滤器 日志的记载
 * @spring-security-oathority :项目名称
 * @com.hundsun.sso.filter.CharacterEncodingAndIpFilter.java 类全路径
 * @2016年12月30日 下午2:33:44
 * @CharacterEncodingAndIpFilter
 *
 */
public class CharacterEncodingAndIpFilter extends CharacterEncodingFilter {
	private static final Logger logger = Logger.getLogger(CharacterEncodingAndIpFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String ip = WebUtils.retriveClientIp(request);
		WebUtils.setIp(ip);
		logger.debug("user enter the system and User's Ip is :" + ip);
		super.doFilterInternal(request, response, filterChain);
	}

}
