package com.nothing.ucenter.social.qq.api.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.nothing.ucenter.social.qq.api.QQ
import com.nothing.ucenter.social.qq.api.QQUserInfo
import org.apache.commons.lang.StringUtils
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding
import org.springframework.social.oauth2.TokenStrategy

class QQImpl
/**
 * 构造方法获取openId
 */
(accessToken: String,
 /**
  * appId 配置文件读取
  */
 private val appId: String) : AbstractOAuth2ApiBinding(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER), QQ {
    /**
     * openId 请求QQ_URL_GET_OPENID返回
     */
    private val openId: String
    /**
     * 工具类
     */
    private val objectMapper = ObjectMapper()

    override val userInfo: QQUserInfo
        get() {
            val url = String.format(QQ_URL_GET_USER_INFO, appId, openId)
            val result = restTemplate.getForObject(url, String::class.java)

            //log.info("【QQImpl】 QQ_URL_GET_USER_INFO={} result={}", QQ_URL_GET_USER_INFO, result)

            var userInfo: QQUserInfo? = null
            try {
                userInfo = objectMapper.readValue(result, QQUserInfo::class.java)
                userInfo.openId = openId
                return userInfo
            } catch (e: Exception) {
                throw RuntimeException("获取用户信息失败", e)
            }

        }

    init {

        val url = String.format(QQ_URL_GET_OPENID, accessToken)
        val result = restTemplate.getForObject(url, String::class.java)

        //log.info("【QQImpl】 QQ_URL_GET_OPENID={} result={}", QQ_URL_GET_OPENID, result)

        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}")
    }//access_token作为查询参数来携带。

    companion object {

        //http://wiki.connect.qq.com/openapi%E8%B0%83%E7%94%A8%E8%AF%B4%E6%98%8E_oauth2-0
        private val QQ_URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s"
        //http://wiki.connect.qq.com/get_user_info(access_token由父类提供)
        private val QQ_URL_GET_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s"
    }
}