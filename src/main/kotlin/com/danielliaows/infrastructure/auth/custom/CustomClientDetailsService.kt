package com.danielliaows.infrastructure.auth.custom

import com.danielliaows.infrastructure.auth.mapper.ClientMapper
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException
import org.springframework.security.oauth2.provider.ClientDetails
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.ClientRegistrationException
import org.springframework.stereotype.Component

@Component
class CustomClientDetailsService(
        private val clientMapper: ClientMapper
) : ClientDetailsService {

    @Throws(UnapprovedClientAuthenticationException::class)
    override fun loadClientByClientId(clientId: String): ClientDetails? {
        val client = clientMapper.findByClientId(clientId) ?: throw UnapprovedClientAuthenticationException("Client: $clientId not found.")
        return CustomClientDetails(client)
    }
}