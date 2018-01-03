package com.nothing.backend

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import javax.servlet.http.HttpServletResponse


@SpringBootApplication
@EnableResourceServer
@EnableEurekaClient
class BackendMain : ResourceServerConfigurerAdapter() {
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

    companion object {
        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(BackendMain::class.java, *args)
        }
    }
}