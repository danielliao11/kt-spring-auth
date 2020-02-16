package com.danielliaows.infrastructure.auth.handler

import com.danielliaows.infrastructure.auth.custom.CustomClientDetailsService
import com.danielliaows.infrastructure.auth.custom.CustomUserDetailsService
import com.danielliaows.infrastructure.auth.param.LoginParam
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.TokenRequest
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices
import org.springframework.stereotype.Service
import java.lang.Exception
import java.nio.charset.StandardCharsets
import java.util.*
import javax.servlet.http.HttpServletRequest

@Service
class LoginHandler(
        private val clientDetailsService: CustomClientDetailsService,
        private val userDetailsService: CustomUserDetailsService,
        private val authorizationServerTokenServices: AuthorizationServerTokenServices,
        private val passwordEncoder: PasswordEncoder
) {

    @Throws(
            UnapprovedClientAuthenticationException::class,
            BadCredentialsException::class,
            UsernameNotFoundException::class
    )
    fun login(request: HttpServletRequest, loginParam: LoginParam): OAuth2AccessToken {
        val header = request.getHeader("Authorization")
        val client = decodeHeader(header)
        val clientId = client[0]
        val clientSecret = client[1]
        val clientDetails = clientDetailsService.loadClientByClientId(clientId)
        if (clientDetails == null) {
            throw UnapprovedClientAuthenticationException("Client: $clientId not found.")
        } else if (!passwordEncoder.matches(clientSecret, clientDetails.clientSecret)) {
            throw UnapprovedClientAuthenticationException("Invalid client secret.")
        }
        // Create token request
        val tokenRequest = TokenRequest(emptyMap(), clientId, clientDetails.scope, loginParam.type)
        // Create oauth2 token request
        val oAuth2AccessTokenRequest = tokenRequest.createOAuth2Request(clientDetails)
        // Get UserDetails of current user
        val userDetails = userDetailsService.loadUserByUsername(loginParam.username) ?: throw BadCredentialsException("User: $loginParam.username not found.")
        // Create user authentication
        val authentication = UsernamePasswordAuthenticationToken(userDetails.username, userDetails.password, userDetails.authorities)
        // Create oauth2 authentication
        val oAuth2Authentication = OAuth2Authentication(oAuth2AccessTokenRequest, authentication)
        // Create access token
        return authorizationServerTokenServices.createAccessToken(oAuth2Authentication)
    }

    /**
     * Decode basic token and return an 2 elements array of
     * [clientId, clientSecret]
     */
    @Throws(
            UnapprovedClientAuthenticationException::class,
            BadCredentialsException::class
    )
    fun decodeHeader(header: String?): Array<String> {
        if (header == null || !header.toLowerCase().startsWith("basic ")) {
            throw UnapprovedClientAuthenticationException("Invalid basic token.")
        }
        val base64Token = header.substring(6).toByteArray(StandardCharsets.UTF_8)
        val decoded: ByteArray
        try {
            decoded = Base64.getDecoder().decode(base64Token)
        } catch (e: IllegalArgumentException) {
            throw BadCredentialsException("Failed to decode basic token.")
        }
        val basicToken = String(decoded, StandardCharsets.UTF_8)
        val delimiter = basicToken.indexOf(":")
        if (delimiter == -1) {
            throw BadCredentialsException("Invalid basic token.")
        }
        val tokenArray = arrayOf(basicToken.substring(0, delimiter), basicToken.substring(delimiter + 1))
        if (tokenArray.size != 2) {
            throw BadCredentialsException("Invalid basic token.")
        }
        return tokenArray
    }
}