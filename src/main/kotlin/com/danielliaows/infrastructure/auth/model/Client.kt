package com.danielliaows.infrastructure.auth.model

import com.danielliaows.infrastructure.auth.common.CommonInfo
import com.danielliaows.infrastructure.auth.common.UUIDGenerator
import tk.mybatis.mapper.annotation.KeySql
import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "clients")
data class Client(
        @Id
        @KeySql(genId = UUIDGenerator::class)
        var id: String? = null,

        @Column(name = "client_id")
        val clientId: String? = null,

        @Column(name = "client_secret")
        var clientSecret: String? = null,

        @Column(name = "resource_ids")
        var resourceIds: String? = null,

        @Column(name = "access_token_validity_seconds")
        var accessTokenValiditySeconds: Int? = null,

        @Column(name = "refresh_token_validity_seconds")
        var refreshTokenValiditySeconds: Int? = null,

        @Column(name = "scopes")
        var scopes: String? = null,

        @Column(name = "authorized_grant_types")
        var authorizedGrantTypes: String? = null,

        @Column(name = "registered_redirect_uris")
        var registeredRedirectUris: String? = null,

        @Column(name = "authorities")
        var authorities: String? = null
) : CommonInfo()