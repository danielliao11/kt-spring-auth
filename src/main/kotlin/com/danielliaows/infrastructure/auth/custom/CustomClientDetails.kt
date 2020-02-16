package com.danielliaows.infrastructure.auth.custom

import com.danielliaows.infrastructure.auth.model.Client
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.provider.ClientDetails

class CustomClientDetails(
        private val client: Client
) : ClientDetails {

    override fun isSecretRequired(): Boolean {
        return true
    }

    override fun getAdditionalInformation(): MutableMap<String, Any> {
        return emptyMap<String, Any>().toMutableMap()
    }

    override fun getAccessTokenValiditySeconds(): Int {
        return client.accessTokenValiditySeconds
    }

    override fun getResourceIds(): MutableSet<String> {
        return client.resourceIds.split("|").toMutableSet()
    }

    override fun getClientId(): String {
        return client.clientId
    }

    override fun isAutoApprove(scope: String?): Boolean {
        return true
    }

    override fun getAuthorities(): MutableCollection<GrantedAuthority> {
        return client.authorities.split("|").map { SimpleGrantedAuthority(it) }.toMutableList()
    }

    override fun getRefreshTokenValiditySeconds(): Int {
        return client.refreshTokenValiditySeconds
    }

    override fun getClientSecret(): String {
        return client.clientSecret
    }

    override fun getRegisteredRedirectUri(): MutableSet<String> {
        return client.registeredRedirectUris.split("|").toMutableSet()
    }

    override fun isScoped(): Boolean {
        return true
    }

    override fun getScope(): MutableSet<String> {
        return client.scopes.split("|").toMutableSet()
    }

    override fun getAuthorizedGrantTypes(): MutableSet<String> {
        return client.authorizedGrantTypes.split("|").toMutableSet()
    }
}