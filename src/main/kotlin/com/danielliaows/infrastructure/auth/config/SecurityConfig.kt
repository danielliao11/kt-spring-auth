package com.danielliaows.infrastructure.auth.config

import com.danielliaows.infrastructure.auth.common.UUIDGenerator
import com.danielliaows.infrastructure.auth.custom.CustomUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig(
        private val customUserDetailsService: CustomUserDetailsService
) : WebSecurityConfigurerAdapter() {

    override fun userDetailsService(): UserDetailsService {
        return customUserDetailsService
    }

    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/open/**").permitAll()
                .anyRequest().authenticated()
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}

fun main(args: Array<String>) {
    println(UUIDGenerator().genId("", ""))
    println(BCryptPasswordEncoder().encode("123456"))
    println(BCryptPasswordEncoder().matches("123456", "\$2a\$10\$OFm3cNFNhhl8SlBnjhgx5etqYQM5mmjOrGh/4Iia6QAT1sOJW7ZFC"))
}