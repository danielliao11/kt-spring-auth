package com.danielliaows.infrastructure.auth.custom

import com.danielliaows.infrastructure.auth.mapper.UserMapper
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class CustomUserDetailsService(
        private val userMapper: UserMapper
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails? {
        val user = userMapper.findByUsername(username) ?: throw UsernameNotFoundException(username)
        return CustomUserDetails(user)
    }
}