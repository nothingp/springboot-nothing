package com.nothing.ucenter.social.weixin_snsapi.connect

import com.nothing.ucenter.social.weixin_snsapi.api.Weixin
import org.springframework.social.connect.ApiAdapter
import org.springframework.social.connect.Connection
import org.springframework.social.connect.ConnectionData
import org.springframework.social.connect.support.OAuth2Connection
import org.springframework.social.connect.support.OAuth2ConnectionFactory
import org.springframework.social.oauth2.AccessGrant
import org.springframework.social.oauth2.OAuth2ServiceProvider

class WeixinConnectionFactory
/**
 * @param appId
 * @param appSecret
 */
(providerId: String, appId: String, appSecret: String) : OAuth2ConnectionFactory<Weixin>(providerId, WeixinServiceProvider(appId, appSecret), WeixinAdapter()) {

    private val oAuth2ServiceProvider: OAuth2ServiceProvider<Weixin>
        get() = serviceProvider as OAuth2ServiceProvider<Weixin>

    /**
     * 由于微信的openId是和accessToken一起返回的，所以在这里直接根据accessToken设置providerUserId即可，不用像QQ那样通过QQAdapter来获取
     */
    override fun extractProviderUserId(accessGrant: AccessGrant?): String? {
        return (accessGrant as? WeixinAccessGrant)?.openId
    }

    /* (non-Javadoc)
     * @see org.springframework.social.connect.support.OAuth2ConnectionFactory#createConnection(org.springframework.social.oauth2.AccessGrant)
     */
    override fun createConnection(accessGrant: AccessGrant): Connection<Weixin> {
        return OAuth2Connection<Weixin>(providerId, extractProviderUserId(accessGrant), accessGrant.accessToken,
                accessGrant.refreshToken, accessGrant.expireTime, oAuth2ServiceProvider, getApiAdapter(extractProviderUserId(accessGrant)))
    }

    /* (non-Javadoc)
     * @see org.springframework.social.connect.support.OAuth2ConnectionFactory#createConnection(org.springframework.social.connect.ConnectionData)
     */
    override fun createConnection(data: ConnectionData): Connection<Weixin> {
        return OAuth2Connection<Weixin>(data, oAuth2ServiceProvider, getApiAdapter(data.providerUserId))
    }

    private fun getApiAdapter(providerUserId: String?): ApiAdapter<Weixin> {
        return WeixinAdapter(providerUserId!!)
    }

}