package com.danielliaows.infrastructure.auth.custom

import com.danielliaows.infrastructure.auth.mapper.ClientMapper
import org.springframework.security.oauth2.provider.ClientDetails
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.ClientRegistrationException
import org.springframework.stereotype.Component

@Component
class CustomClientDetailsService(
        private val clientMapper: ClientMapper
) : ClientDetailsService {

    override fun loadClientByClientId(clientId: String): ClientDetails? {
        val client = clientMapper.findByClientId(clientId) ?: throw ClientRegistrationException(clientId)
        return CustomClientDetails(client)
    }
}