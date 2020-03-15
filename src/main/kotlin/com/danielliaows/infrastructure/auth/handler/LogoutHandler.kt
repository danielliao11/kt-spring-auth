package com.danielliaows.infrastructure.auth.handler

import org.springframework.http.HttpHeaders
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.stereotype.Service
import javax.annotation.Resource
import javax.servlet.http.HttpServletRequest

@Service
class LogoutHandler {

    @Throws(InvalidTokenException::class)
    fun logout(request: HttpServletRequest) {
        val header = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (header == null || !header.toLowerCase().startsWith("bearer ")) {
            throw InvalidTokenException("Invalid bearer token.")
        }
        val tokenValue = header.substring(7)
        val oAuth2AccessToken = tokenStore.readAccessToken(tokenValue)
        tokenStore.removeAccessToken(oAuth2AccessToken)
    }

    @Resource(name = "tokenStore")
    lateinit var tokenStore: TokenStore
}