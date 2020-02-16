package com.danielliaows.infrastructure.boilerplate.auth.custom

import org.springframework.security.oauth2.provider.ClientDetails
import org.springframework.security.oauth2.provider.ClientDetailsService

class CustomClientDetailsService : ClientDetailsService {

    override fun loadClientByClientId(clientId: String?): ClientDetails {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}