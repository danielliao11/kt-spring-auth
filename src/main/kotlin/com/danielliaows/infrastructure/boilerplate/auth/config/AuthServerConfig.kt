package com.danielliaows.infrastructure.boilerplate.auth.config

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
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore
import javax.sql.DataSource

@Configuration
@EnableAuthorizationServer
class AuthServerConfig(
    private val authenticationManager: AuthenticationManager,
    private val dataSource: DataSource,
    private val redisConnectionFactory: RedisConnectionFactory
) : AuthorizationServerConfigurerAdapter() {

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints
            .authenticationManager(authenticationManager)
            .tokenStore(tokenStore())
            .accessTokenConverter(jwtAccessTokenConverter())
    }

    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        clients.withClientDetails(clientDetailService())
    }

    fun clientDetailService(): ClientDetailsService {
        return JdbcClientDetailsService(dataSource)
    }

    @Bean
    fun tokenStore(): TokenStore {
        return RedisTokenStore(redisConnectionFactory)
    }

    @Bean
    fun jwtAccessTokenConverter(): JwtAccessTokenConverter {
        val converter = JwtAccessTokenConverter()
        val keyStoreFactory = KeyStoreKeyFactory(ClassPathResource(""), "".toCharArray())
        converter.setKeyPair(keyStoreFactory.getKeyPair(""))
        return converter
    }
}