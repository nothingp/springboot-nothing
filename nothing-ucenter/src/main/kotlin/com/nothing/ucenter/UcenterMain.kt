package com.nothing.ucenter

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.feign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
class UcenterMain : WebSecurityConfigurerAdapter() {

    @Autowired
    private val userDetailsService: UserDetailsService? = null


    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().disable().authorizeRequests().anyRequest().permitAll()
    }

    @Bean
    fun securityEvaluationContextExtension(): SecurityEvaluationContextExtension {
        return SecurityEvaluationContextExtension()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
    }

    //不定义没有password grant_type
    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    companion object {
        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(UcenterMain::class.java, *args)
        }
    }


}