package com.danielliaows.infrastructure.auth.custom

import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.TokenEnhancer
import org.springframework.stereotype.Component

/**
 * Add custom information into token response.
 */
@Component
class CustomTokenEnhancer : TokenEnhancer {

    override fun enhance(accessToken: OAuth2AccessToken, authentication: OAuth2Authentication): OAuth2AccessToken {
        val additionalInfo = HashMap<String, Any>()
        val userAuthentication = authentication.userAuthentication
        additionalInfo["authorities"] = userAuthentication.authorities
        accessToken.additionalInformation.putAll(additionalInfo)
        return accessToken
    }
}