package com.danielliaows.infrastructure.auth.model

import com.danielliaows.infrastructure.auth.common.UUIDGenerator
import tk.mybatis.mapper.annotation.KeySql
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "auth_types")
data class AuthType(
        @Id
        @KeySql(genId = UUIDGenerator::class)
        private val id: String,
        private val userId: String,
        private var type: String,
        private var content: String,
        private val createdAt: Long,
        private var updatedAt: Long
)