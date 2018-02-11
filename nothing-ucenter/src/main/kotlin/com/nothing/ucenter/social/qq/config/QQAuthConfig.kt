package com.nothing.ucenter.social.qq.config

import com.nothing.ucenter.social.SocialConnectView
import com.nothing.ucenter.social.qq.connect.QQConnectionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.encrypt.Encryptors
import org.springframework.social.connect.ConnectionFactory
import org.springframework.social.connect.ConnectionFactoryLocator
import org.springframework.social.connect.UsersConnectionRepository
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository
import org.springframework.web.servlet.View
import javax.sql.DataSource

@Configuration
class QQAuthConfig : SocialAutoConfigurerAdapter() {

    @Autowired
    private val dataSource: DataSource? = null

    override fun createConnectionFactory(): ConnectionFactory<*> {
        return QQConnectionFactory("qq", "101386962", "2a0f820407df400b84a854d054be8b6a")
    }

    override fun getUsersConnectionRepository(connectionFactoryLocator: ConnectionFactoryLocator): UsersConnectionRepository {
        return JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator, Encryptors.noOpText())
    }

    /**
     * /connect/qq POST请求,绑定微信返回connect/weixinConnected视图
     * /connect/qq DELETE请求,解绑返回connect/weixinConnect视图
     * @return
     */
    @Bean("connect/qqConnect", "connect/qqConnected")
    @ConditionalOnMissingBean(name = arrayOf("qqConnectedView"))
    fun qqConnectedView(): View {
        return SocialConnectView()
    }
}