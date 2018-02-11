package com.nothing.ucenter.social.weixin_snsapi.connect

import com.nothing.ucenter.social.weixin_snsapi.api.Weixin
import com.nothing.ucenter.social.weixin_snsapi.api.impl.WeiXinImpl
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider

class WeixinServiceProvider(appId: String, appSecret: String) : AbstractOAuth2ServiceProvider<Weixin>(WeixinOAuth2Template(appId, appSecret, WEIXIN_URL_AUTHORIZE, WEIXIN_URL_ACCESS_TOKEN)) {

    override fun getApi(accessToken: String): Weixin {
        return WeiXinImpl(accessToken)
    }

    companion object {

        /**
         * 微信获取授权码的url
         */
        private val WEIXIN_URL_AUTHORIZE = "https://open.weixin.qq.com/connect/oauth2/authorize"
        /**
         * 微信获取accessToken的url(微信在获取accessToken时也已经返回openId)
         */
        private val WEIXIN_URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token"
    }
}