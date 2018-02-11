package com.nothing.ucenter.social.qq.connect

import org.apache.commons.lang.StringUtils
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.social.oauth2.AccessGrant
import org.springframework.social.oauth2.OAuth2Template
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import java.nio.charset.Charset

class QQOAuth2Template(clientId: String, clientSecret: String, authorizeUrl: String, accessTokenUrl: String) : OAuth2Template(clientId, clientSecret, authorizeUrl, accessTokenUrl) {
    init {
        setUseParametersForClientAuthentication(true)
    }

    override fun postForAccessGrant(accessTokenUrl: String, parameters: MultiValueMap<String, String>): AccessGrant {
        val responseStr = restTemplate.postForObject(accessTokenUrl, parameters, String::class.java)

        //log.info("【QQOAuth2Template】获取accessToke的响应：responseStr={}" + responseStr)

        val items = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr, "&")
        //http://wiki.connect.qq.com/使用authorization_code获取access_token
        //access_token=FE04************************CCE2&expires_in=7776000&refresh_token=88E4************************BE14
        val accessToken = StringUtils.substringAfterLast(items[0], "=")
        val expiresIn = StringUtils.substringAfterLast(items[1], "=").toLong()
        val refreshToken = StringUtils.substringAfterLast(items[2], "=")

        return AccessGrant(accessToken, null, refreshToken, expiresIn)
    }


    /**
     * 坑，日志debug模式才打印出来 处理qq返回的text/html 类型数据
     *
     * @return
     */
    override fun createRestTemplate(): RestTemplate {
        val restTemplate = super.createRestTemplate()
        restTemplate.messageConverters.add(StringHttpMessageConverter(Charset.forName("UTF-8")))
        return restTemplate
    }
}