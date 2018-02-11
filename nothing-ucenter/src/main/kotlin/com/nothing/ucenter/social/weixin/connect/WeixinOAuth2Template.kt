package com.nothing.ucenter.social.weixin.connect

import com.fasterxml.jackson.databind.ObjectMapper
import lombok.extern.slf4j.Slf4j
import org.apache.commons.collections.MapUtils
import org.apache.commons.lang.StringUtils
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider
import org.springframework.social.oauth2.AccessGrant
import org.springframework.social.oauth2.OAuth2Parameters
import org.springframework.social.oauth2.OAuth2Template
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import java.nio.charset.Charset

@Slf4j
class WeixinOAuth2Template(private val clientId: String, private val clientSecret: String, authorizeUrl: String, private val accessTokenUrl: String) : OAuth2Template(clientId, clientSecret, authorizeUrl, accessTokenUrl) {

    init {
        setUseParametersForClientAuthentication(true)
    }

    /* (non-Javadoc)
     * @see org.springframework.social.oauth2.OAuth2Template#exchangeForAccess(java.lang.String, java.lang.String, org.springframework.util.MultiValueMap)
     */
    override fun exchangeForAccess(authorizationCode: String, redirectUri: String,
                                   parameters: MultiValueMap<String, String>?): AccessGrant {

        val accessTokenRequestUrl = StringBuilder(accessTokenUrl)

        accessTokenRequestUrl.append("?appid=" + clientId)
        accessTokenRequestUrl.append("&secret=" + clientSecret)
        accessTokenRequestUrl.append("&code=" + authorizationCode)
        accessTokenRequestUrl.append("&grant_type=authorization_code")
        accessTokenRequestUrl.append("&redirect_uri=" + redirectUri)

        return getAccessToken(accessTokenRequestUrl)
    }

    override fun refreshAccess(refreshToken: String, additionalParameters: MultiValueMap<String, String>?): AccessGrant {

        val refreshTokenUrl = StringBuilder(REFRESH_TOKEN_URL)

        refreshTokenUrl.append("?appid=" + clientId)
        refreshTokenUrl.append("&grant_type=refresh_token")
        refreshTokenUrl.append("&refresh_token=" + refreshToken)

        return getAccessToken(refreshTokenUrl)
    }

    private fun getAccessToken(accessTokenRequestUrl: StringBuilder): AccessGrant {

        //log.info("获取access_token, 请求URL: " + accessTokenRequestUrl.toString())

        val response = restTemplate.getForObject(accessTokenRequestUrl.toString(), String::class.java)

        //log.info("获取access_token, 响应内容: " + response)

        var result: Map<*, *>? = null
        try {
            result = ObjectMapper().readValue(response,Map::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        //返回错误码时直接返回空
        if (StringUtils.isNotBlank(MapUtils.getString(result, "errcode"))) {
            val errcode = MapUtils.getString(result, "errcode")
            val errmsg = MapUtils.getString(result, "errmsg")
            throw RuntimeException("获取access token失败, errcode:$errcode, errmsg:$errmsg")
        }

        val accessToken = WeixinAccessGrant(
                MapUtils.getString(result, "access_token"),
                MapUtils.getString(result, "scope"),
                MapUtils.getString(result, "refresh_token"),
                MapUtils.getLong(result, "expires_in"))

        accessToken.openId = MapUtils.getString(result, "openid")

        return accessToken
    }

    /**
     * 构建获取授权码的请求。也就是引导用户跳转到微信的地址。
     */
    override fun buildAuthenticateUrl(parameters: OAuth2Parameters): String {
        var url = super.buildAuthenticateUrl(parameters)
        url = "$url&appid=$clientId&scope=snsapi_login"
        return url
    }

    override fun buildAuthorizeUrl(parameters: OAuth2Parameters): String {
        return buildAuthenticateUrl(parameters)
    }

    /**
     * 微信返回的contentType是html/text，添加相应的HttpMessageConverter来处理。
     */
    override fun createRestTemplate(): RestTemplate {
        val restTemplate = super.createRestTemplate()
        restTemplate.messageConverters.add(StringHttpMessageConverter(Charset.forName("UTF-8")))
        return restTemplate
    }

    companion object {

        private val REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token"
    }
}