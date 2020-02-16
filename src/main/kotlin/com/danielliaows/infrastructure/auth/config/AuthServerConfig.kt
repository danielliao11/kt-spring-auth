package com.danielliaows.infrastructure.auth.config

import com.danielliaows.infrastructure.auth.custom.CustomClientDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore

@Configuration
@EnableAuthorizationServer
class AuthServerConfig(
    private val authenticationManager: AuthenticationManager,
    private val customClientDetailsService: CustomClientDetailsService,
    private val redisConnectionFactory: RedisConnectionFactory
) : AuthorizationServerConfigurerAdapter() {

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .accessTokenConverter(jwtAccessTokenConverter())
    }

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.withClientDetails(clientDetailService())
    }

    fun clientDetailService(): ClientDetailsService {
        return customClientDetailsService
    }

    @Bean
    fun tokenStore(): TokenStore {
        return RedisTokenStore(redisConnectionFactory)
    }

    @Bean
    fun jwtAccessTokenConverter(): JwtAccessTokenConverter {
        val converter = JwtAccessTokenConverter()
        val keyStoreFactory = KeyStoreKeyFactory(ClassPathResource("auth.jks"), "123456".toCharArray())
        converter.setKeyPair(keyStoreFactory.getKeyPair("auth"))
        return converter
    }
}