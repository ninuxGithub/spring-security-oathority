package com.hundsun.sso.serviceimpl;

import javax.sql.DataSource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import com.hundsun.sso.constant.CacheConstants;

/**
 * 2016/7/26
 * <p/>
 * 扩展默认的 TokenStore, 增加对缓存的支持
 *
 * @author Shengzhao Li
 */
public class CustomJdbcTokenStore extends JdbcTokenStore {


    public CustomJdbcTokenStore(DataSource dataSource) {
        super(dataSource);
    }


    @Cacheable(value = CacheConstants.ACCESS_TOKEN_CACHE, key = "#tokenValue")
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        return super.readAccessToken(tokenValue);
    }


    @CacheEvict(value = CacheConstants.ACCESS_TOKEN_CACHE, key = "#tokenValue")
    public void removeAccessToken(String tokenValue) {
        super.removeAccessToken(tokenValue);
    }


    @Cacheable(value = CacheConstants.REFRESH_TOKEN_CACHE, key = "#token")
    public OAuth2RefreshToken readRefreshToken(String token) {
        return super.readRefreshToken(token);
    }

    @CacheEvict(value = CacheConstants.REFRESH_TOKEN_CACHE, key = "#token")
    public void removeRefreshToken(String token) {
        super.removeRefreshToken(token);
    }


}
