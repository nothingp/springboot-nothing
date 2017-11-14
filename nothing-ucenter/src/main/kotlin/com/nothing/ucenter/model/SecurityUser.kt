package com.nothing.ucenter.model

import org.springframework.security.core.GrantedAuthority


class SecurityUser(user: User?, enabled: Boolean, accountNonExpired: Boolean, credentialsNonExpired: Boolean, accountNonLocked: Boolean, authorities: Collection<GrantedAuthority>) :
        org.springframework.security.core.userdetails.User(user!!.email, user.password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities){
    val user: User? =user
}
