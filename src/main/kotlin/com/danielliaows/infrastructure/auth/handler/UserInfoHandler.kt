package com.danielliaows.infrastructure.auth.handler

import org.springframework.security.oauth2.provider.OAuth2Authentication
import java.security.Principal

class UserInfoHandler {

    fun getDetails(principal: Principal?): Map<String, Any> {
        if (principal == null) {
            return emptyMap()
        }
        val oAuth2Authentication = principal as OAuth2Authentication
        val authentication = oAuth2Authentication.userAuthentication
        return authentication as Map<String, Any>
    }
}