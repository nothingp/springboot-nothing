package com.nothing.ucenter.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore


@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig : AuthorizationServerConfigurerAdapter() {
    @Autowired
    private val authenticationManager: AuthenticationManager? = null


    @Bean
    fun tokenStore(): TokenStore {
        return InMemoryTokenStore()
    }


    @Throws(Exception::class)
    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        endpoints!!
                .authenticationManager(authenticationManager)
               // .userDetailsService(userDetailsService)//若无，refresh_token会有UserDetailsService is required错误
                .tokenStore(tokenStore())
    }

    @Throws(Exception::class)
    override fun configure(security: AuthorizationServerSecurityConfigurer?) {
        security!!
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
    }

    @Throws(Exception::class)
    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        clients!!.inMemory()
                .withClient("android")
                .scopes("xx")
                .secret("android")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .and()
                .withClient("webapp")
                .scopes("xx")
                .authorizedGrantTypes("implicit")
    }
}