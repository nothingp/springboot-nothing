package com.nothing.ucenter.social.weixin_snsapi.config

import com.nothing.ucenter.social.weixin_snsapi.connect.WeixinConnectionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.encrypt.Encryptors
import org.springframework.social.connect.ConnectionFactory
import org.springframework.social.connect.ConnectionFactoryLocator
import org.springframework.social.connect.ConnectionSignUp
import org.springframework.social.connect.UsersConnectionRepository
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository
import sun.security.util.SecurityConstants
import javax.sql.DataSource

@Configuration
class WeixinAuthConfig : SocialAutoConfigurerAdapter() {
    @Autowired
    private val dataSource: DataSource? = null

    @Autowired
    private val myConnectionSignUp: ConnectionSignUp? = null

    override fun createConnectionFactory(): ConnectionFactory<*> {
        return WeixinConnectionFactory("weixin_snsapi", "wxd36f638a1705706e","1f1dfd1dcb5ba5e33279bd4af7300d71")
    }

    override fun getUsersConnectionRepository(connectionFactoryLocator: ConnectionFactoryLocator): UsersConnectionRepository {
        val repository = JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator, Encryptors.noOpText())
        if (myConnectionSignUp != null) {
            repository.setConnectionSignUp(myConnectionSignUp)
        }
        return repository
    }
}