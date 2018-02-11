package com.nothing.ucenter.social.weixin.connect

import com.nothing.ucenter.social.weixin.api.Weixin
import org.springframework.social.connect.ApiAdapter
import org.springframework.social.connect.ConnectionValues
import org.springframework.social.connect.UserProfile


/**
 * 微信 api适配器，将微信 api的数据模型转为spring social的标准模型。
 */
class WeixinAdapter : ApiAdapter<Weixin> {

    private var openId: String = ""

    constructor() {}

    constructor(openId: String) {
        this.openId = openId
    }

    override fun test(api: Weixin): Boolean {
        return true
    }

    override fun setConnectionValues(api: Weixin, values: ConnectionValues) {
        val userInfo = api.getUserInfo(openId)
        values.setProviderUserId(userInfo?.openid)
        values.setDisplayName(userInfo?.nickname)
        values.setImageUrl(userInfo?.headimgurl)
    }

    override fun fetchUserProfile(api: Weixin): UserProfile? {
        return null
    }

    override fun updateStatus(api: Weixin, message: String) {

    }
}
