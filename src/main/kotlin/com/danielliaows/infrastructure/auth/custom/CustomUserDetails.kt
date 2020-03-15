package com.danielliaows.infrastructure.auth.custom

import com.danielliaows.infrastructure.auth.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(
        private val user: User
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return user.authorities!!
                .split("|")
                .map { SimpleGrantedAuthority(it) }
                .toMutableList()
    }

    override fun isEnabled(): Boolean {
        return user.isEnable!!
    }

    override fun getUsername(): String {
        return user.username!!
    }

    override fun isCredentialsNonExpired(): Boolean {
        return false
    }

    override fun getPassword(): String {
        return user.password!!
    }

    override fun isAccountNonExpired(): Boolean {
        return false
    }

    override fun isAccountNonLocked(): Boolean {
        return false
    }
}