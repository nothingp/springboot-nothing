package com.nothing.ucenter.social.qq.connect

import com.nothing.ucenter.social.qq.api.QQ
import org.springframework.social.connect.support.OAuth2ConnectionFactory

class QQConnectionFactory(providerId: String, appId: String, appSecret: String) : OAuth2ConnectionFactory<QQ>(providerId, QQServiceProvider(appId, appSecret), QQAdapter())