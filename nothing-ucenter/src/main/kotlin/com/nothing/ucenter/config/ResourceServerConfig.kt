package com.nothing.ucenter.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.social.security.SpringSocialConfigurer
import javax.servlet.http.HttpServletResponse


@Configuration
@EnableResourceServer
class ResourceServerConfig : ResourceServerConfigurerAdapter() {

    @Autowired
    private val merryyouSpringSocialConfigurer: SpringSocialConfigurer? = null

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint { request, response, authException -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED) }
                .and()
                .apply(merryyouSpringSocialConfigurer!!)
                .and()
                .authorizeRequests().antMatchers(
                "/register",
                "/socialRegister",//社交账号注册和绑定页面
                "/user/register",//处理社交注册请求
                "/social/info",//获取当前社交用户信息
                "/social/info",
                "/**/*.js",
                "/**/*.css",
                "/**/*.jpg",
                "/**/*.png",
                "/**/*.woff2",
                "/code/image")
                .permitAll()//以上的请求都不需要认证
                .and()
                .httpBasic()
    }
}