package com.nothing.ucenter.social.qq.connect

import com.nothing.ucenter.social.qq.api.QQ
import com.nothing.ucenter.social.qq.api.impl.QQImpl
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider


class QQServiceProvider(private val appId: String, appSecret: String) : AbstractOAuth2ServiceProvider<QQ>(QQOAuth2Template(appId, appSecret, QQ_URL_AUTHORIZE, QQ_URL_ACCESS_TOKEN)) {

    override fun getApi(accessToken: String): QQ {

        return QQImpl(accessToken, appId)
    }

    companion object {

        /**
         * 获取code
         */
        private val QQ_URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize"
        /**
         * 获取access_token 也就是令牌
         */
        private val QQ_URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token"
    }
}