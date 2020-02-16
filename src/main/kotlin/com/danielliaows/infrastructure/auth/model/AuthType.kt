package com.danielliaows.infrastructure.auth.model

import com.danielliaows.infrastructure.auth.common.UUIDGenerator
import tk.mybatis.mapper.annotation.KeySql
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "auth_types")
data class AuthType(
        @Id
        @KeySql(genId = UUIDGenerator::class)
        val id: String,
        val userId: String,
        var type: String,
        var content: String,
        val createdAt: Long,
        var updatedAt: Long
)