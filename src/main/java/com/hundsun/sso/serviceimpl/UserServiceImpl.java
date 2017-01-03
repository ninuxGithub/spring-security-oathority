package com.hundsun.sso.serviceimpl;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import com.hundsun.sso.domain.User;
import com.hundsun.sso.dto.UserDto;
import com.hundsun.sso.dto.UserFormDto;
import com.hundsun.sso.dto.UserJsonDto;
import com.hundsun.sso.dto.UserOverviewDto;
import com.hundsun.sso.repository.UserRepository;
import com.hundsun.sso.security.UserDetail;
import com.hundsun.sso.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null || user.getArchived()) {
			throw new UsernameNotFoundException("Not found any user for username[" + username + "]");
		}

		// UserDetail use override constructor to build a user Object
		return new UserDetail(user);
	}

	@Override
	public UserJsonDto loadCurrentUserJsonDto() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		final Object principal = authentication.getPrincipal();

		if (authentication instanceof OAuth2Authentication && (principal instanceof String
				|| principal instanceof org.springframework.security.core.userdetails.User)) {
			logger.info("loadCurrentUserJsonDto if syntext :" + principal);
			return loadOauthUserJsonDto((OAuth2Authentication) authentication);
		} else {
			logger.info("loadCurrentUserJsonDto else syntext:" + principal);
			final UserDetail userDetails = (UserDetail) principal;
			return new UserJsonDto(userRepository.findByGuid(userDetails.getUser().getGuid()));
		}
	}

	@Override
	public UserOverviewDto loadUserOverviewDto(UserOverviewDto overviewDto) {
		List<User> users = userRepository.findUsersByUsername(overviewDto.getUsername());
		overviewDto.setUserDtos(UserDto.toDtos(users));
		return overviewDto;
	}

	@Override
	public boolean isExistedUsername(String username) {
		final User user = userRepository.findByUsername(username);
		return user != null;
	}

	@Override
	public String saveUser(UserFormDto formDto) {
		User user = formDto.newUser();
		userRepository.saveUser(user);
		return user.getGuid();
	}

	private UserJsonDto loadOauthUserJsonDto(OAuth2Authentication oAuth2Authentication) {
		UserJsonDto userJsonDto = new UserJsonDto();
		userJsonDto.setUsername(oAuth2Authentication.getName());

		final Collection<GrantedAuthority> authorities = oAuth2Authentication.getAuthorities();
		for (GrantedAuthority authority : authorities) {
			userJsonDto.getPrivileges().add(authority.getAuthority());
		}

		return userJsonDto;
	}

}
