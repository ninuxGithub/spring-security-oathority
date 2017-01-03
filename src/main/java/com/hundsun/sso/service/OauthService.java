package com.hundsun.sso.service;

import java.util.List;

import com.hundsun.sso.dto.OauthClientDetailsDto;
import com.hundsun.sso.security.OauthClientDetails;

public interface OauthService {

	OauthClientDetails loadOauthClientDetails(String clientId);

	List<OauthClientDetailsDto> loadAllOauthClientDetailsDtos();

	void archiveOauthClientDetails(String clientId);

	OauthClientDetailsDto loadOauthClientDetailsDto(String clientId);

	void registerClientDetails(OauthClientDetailsDto formDto);

}
