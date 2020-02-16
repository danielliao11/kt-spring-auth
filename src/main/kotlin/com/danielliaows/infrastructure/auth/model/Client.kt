package com.danielliaows.infrastructure.auth.model

import com.danielliaows.infrastructure.auth.common.UUIDGenerator
import tk.mybatis.mapper.annotation.KeySql
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "clients")
data class Client(
        @Id
        @KeySql(genId = UUIDGenerator::class)
        val id: String,
        val clientId: String,
        var clientSecret: String,
        var resourceIds: String,
        var accessTokenValiditySeconds: Int,
        var refreshTokenValiditySeconds: Int,
        var scopes: String,
        var authorizedGrantTypes: String,
        var registeredRedirectUris: String,
        var authorities: String,
        val createdAt: Long,
        var updatedAt: Long
)