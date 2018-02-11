package com.nothing.ucenter.social.weixin_snsapi.api.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.nothing.ucenter.social.weixin_snsapi.api.Weixin
import com.nothing.ucenter.social.weixin_snsapi.api.WeixinUserInfo
import org.apache.commons.lang.StringUtils
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding
import org.springframework.social.oauth2.TokenStrategy
import java.nio.charset.Charset


class WeiXinImpl(accessToken: String) : AbstractOAuth2ApiBinding(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER), Weixin {

    private val objectMapper = ObjectMapper()

    /**
     * 获取用户信息
     *
     * @param openId
     * @return
     */
    override fun getUserInfo(openId: String): WeixinUserInfo? {
        val url = WEIXIN_URL_GET_USER_INFO + openId

        val result = restTemplate.getForObject(url, String::class.java)
        if (StringUtils.contains(result, "errcode")) {
            return null
        }

        var userInfo: WeixinUserInfo? = null

        try {
            userInfo = objectMapper.readValue(result, WeixinUserInfo::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return userInfo
    }

    /**
     * 使用utf-8 替换默认的ISO-8859-1编码
     * @return
     */
    override fun getMessageConverters(): List<HttpMessageConverter<*>> {
        val messageConverters = super.getMessageConverters()
        messageConverters.removeAt(0)
        messageConverters.add(StringHttpMessageConverter(Charset.forName("UTF-8")))
        return messageConverters
    }

    companion object {

        /**
         * 获取用户信息的url
         */
        private val WEIXIN_URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?openid="
    }
}