package com.nothing.ucenter.social.qq.connect

import com.nothing.ucenter.social.qq.api.QQ
import org.springframework.social.connect.ApiAdapter
import org.springframework.social.connect.ConnectionValues
import org.springframework.social.connect.UserProfile

class QQAdapter : ApiAdapter<QQ> {
    override fun test(api: QQ): Boolean {
        return true
    }

    override fun setConnectionValues(api: QQ, values: ConnectionValues) {
        val userInfo = api.userInfo

        values.setProviderUserId(userInfo.openId)//openId 唯一标识
        values.setDisplayName(userInfo.nickname)
        values.setImageUrl(userInfo.figureurl_qq_1)
        values.setProfileUrl(null)
    }

    override fun fetchUserProfile(api: QQ): UserProfile? {
        return null
    }

    override fun updateStatus(api: QQ, message: String) {

    }
}