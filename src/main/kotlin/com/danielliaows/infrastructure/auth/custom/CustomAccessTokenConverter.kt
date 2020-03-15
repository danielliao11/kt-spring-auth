package com.danielliaows.infrastructure.auth.custom

import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter
import org.springframework.stereotype.Component

/**
 * Add custom information into jwt token.
 */
@Component
class CustomAccessTokenConverter : DefaultAccessTokenConverter() {

    override fun convertAccessToken(token: OAuth2AccessToken, authentication: OAuth2Authentication): MutableMap<String, *> {
        val additionalInfo = super.convertAccessToken(token, authentication) as MutableMap<String, Any>
        val userAuthentication = authentication.userAuthentication
        additionalInfo["authorities"] = userAuthentication.authorities
        return additionalInfo
    }
}