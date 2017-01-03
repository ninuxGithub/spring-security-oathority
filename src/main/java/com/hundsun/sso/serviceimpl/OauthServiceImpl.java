package com.hundsun.sso.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.sso.dto.OauthClientDetailsDto;
import com.hundsun.sso.repository.OauthRepository;
import com.hundsun.sso.security.OauthClientDetails;
import com.hundsun.sso.service.OauthService;

@Service("oauthService")
public class OauthServiceImpl implements OauthService {
	@Autowired
	private OauthRepository oauthRepository;

	@Override
	public OauthClientDetails loadOauthClientDetails(String clientId) {
		return oauthRepository.findOauthClientDetails(clientId);
	}

	@Override
	public List<OauthClientDetailsDto> loadAllOauthClientDetailsDtos() {
		List<OauthClientDetails> clientDetailses = oauthRepository.findAllOauthClientDetails();
		return OauthClientDetailsDto.toDtos(clientDetailses);
	}

	@Override
	public void archiveOauthClientDetails(String clientId) {
		oauthRepository.updateOauthClientDetailsArchive(clientId, true);
	}

	@Override
	public OauthClientDetailsDto loadOauthClientDetailsDto(String clientId) {
		final OauthClientDetails oauthClientDetails = oauthRepository.findOauthClientDetails(clientId);
		return oauthClientDetails != null ? new OauthClientDetailsDto(oauthClientDetails) : null;
	}

	@Override
	public void registerClientDetails(OauthClientDetailsDto formDto) {
		OauthClientDetails clientDetails = formDto.OauthClientDetailDto2Domain();
		oauthRepository.saveOauthClientDetails(clientDetails);
	}
}
