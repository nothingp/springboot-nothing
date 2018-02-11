package com.nothing.ucenter.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.social.security.SocialUserDetails


class SecurityUser(user: User?, enabled: Boolean, accountNonExpired: Boolean, credentialsNonExpired: Boolean, accountNonLocked: Boolean, authorities: Collection<GrantedAuthority>) :
        org.springframework.security.core.userdetails.User(user!!.email, user.password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities), SocialUserDetails {
    override fun getUserId(): String {
       return this.username
    }

    val user: User? =user
}
