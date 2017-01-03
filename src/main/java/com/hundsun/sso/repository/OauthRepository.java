package com.hundsun.sso.repository;

import java.util.List;

import com.hundsun.sso.security.OauthClientDetails;

public interface OauthRepository extends Repository {

	OauthClientDetails findOauthClientDetails(String clientId);

	List<OauthClientDetails> findAllOauthClientDetails();

	void updateOauthClientDetailsArchive(String clientId, boolean archive);

	void saveOauthClientDetails(OauthClientDetails clientDetails);

}
