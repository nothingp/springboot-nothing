package com.nothing.ucenter.service

import com.nothing.ucenter.model.Role
import com.nothing.ucenter.model.SecurityUser
import com.nothing.ucenter.repository.RoleRepository
import com.nothing.ucenter.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.social.security.SocialUserDetails
import org.springframework.social.security.SocialUserDetailsService
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import javax.transaction.Transactional


@Transactional
@Service
class UserDetailsService :org.springframework.security.core.userdetails.UserDetailsService, SocialUserDetailsService {


    @Autowired
    val userRepository: UserRepository? = null

    @Autowired
    val roleRepository: RoleRepository? = null


    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(account: String): UserDetails {
        val user = userRepository!!.findFirstByEmail(account);

        return SecurityUser(user,
                true, true, true, true,
                getAuthorities(roleRepository!!.findAll()))

    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUserId(userId: String): SocialUserDetails {
        val user = userRepository!!.findFirstByEmail(userId);

        return SecurityUser(user,
                true, true, true, true,
                getAuthorities(roleRepository!!.findAll()))

    }
    // UTIL
    fun getAuthorities(roles: Collection<Role>): Collection<GrantedAuthority> {
        return getGrantedAuthorities(getPrivileges(roles))
    }

    /**
     * 获取角色
     */
    private fun getPrivileges(roles: Collection<Role>): List<String> {
        return roles!!.stream()
                .map({ role -> "ROLE_" + role.name})
                .collect(Collectors.toList<String>())
    }

    /**
     * 获取权限
     */
    private fun getGrantedAuthorities(privileges: List<String>): List<GrantedAuthority> {
        return privileges!!.stream()
                .map({ privilege -> SimpleGrantedAuthority(privilege)})
                .collect(Collectors.toList<GrantedAuthority>())
    }

}