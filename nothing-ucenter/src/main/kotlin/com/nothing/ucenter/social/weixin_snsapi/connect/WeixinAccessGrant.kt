package com.nothing.ucenter.social.weixin_snsapi.connect

import org.springframework.social.oauth2.AccessGrant

class WeixinAccessGrant : AccessGrant {

    var openId: String? = null

    constructor() : super("") {}

    constructor(accessToken: String, scope: String, refreshToken: String, expiresIn: Long?) : super(accessToken, scope, refreshToken, expiresIn) {}
}