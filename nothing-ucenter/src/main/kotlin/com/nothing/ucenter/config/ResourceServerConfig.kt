package com.nothing.ucenter.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import javax.servlet.http.HttpServletResponse


@Configuration
@EnableResourceServer
class ResourceServerConfig : ResourceServerConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint { request, response, authException -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED) }
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
    }
}