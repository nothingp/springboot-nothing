package com.nothing.ucenter.social

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.encrypt.Encryptors
import org.springframework.social.config.annotation.EnableSocial
import org.springframework.social.config.annotation.SocialConfigurerAdapter
import org.springframework.social.connect.ConnectionFactoryLocator
import org.springframework.social.connect.UsersConnectionRepository
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository
import org.springframework.social.connect.web.ProviderSignInUtils
import org.springframework.social.security.SpringSocialConfigurer
import javax.sql.DataSource


@Configuration
@EnableSocial
class SocialConfig : SocialConfigurerAdapter() {

    @Autowired
    private val dataSource: DataSource? = null

    /**
     * 社交登录配类
     *
     * @return
     */
    @Bean
    fun merryyouSocialSecurityConfig(): SpringSocialConfigurer {
        val filterProcessesUrl = "/login"
        return MerryyouSpringSocialConfigurer(filterProcessesUrl)
    }

    override fun getUsersConnectionRepository(connectionFactoryLocator: ConnectionFactoryLocator): UsersConnectionRepository {
        return JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator, Encryptors.noOpText())
    }

    /**
     * 处理注册流程的工具类
     *
     * @param factoryLocator
     * @return
     */
    @Bean
    fun providerSignInUtils(factoryLocator: ConnectionFactoryLocator): ProviderSignInUtils {
        return ProviderSignInUtils(factoryLocator, getUsersConnectionRepository(factoryLocator))
    }

}