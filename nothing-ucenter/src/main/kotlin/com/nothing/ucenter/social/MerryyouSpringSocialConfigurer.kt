package com.nothing.ucenter.social

import org.springframework.social.security.SocialAuthenticationFilter
import org.springframework.social.security.SpringSocialConfigurer


class MerryyouSpringSocialConfigurer(private val filterProcessesUrl: String) : SpringSocialConfigurer() {

    override fun <T> postProcess(`object`: T): T {
        val filter = super.postProcess(`object`) as SocialAuthenticationFilter
        filter.setFilterProcessesUrl(filterProcessesUrl)
        filter.setSignupUrl("/socialRegister")
        return filter as T
    }
}