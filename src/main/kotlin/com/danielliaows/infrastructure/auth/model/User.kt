package com.danielliaows.infrastructure.auth.model

import com.danielliaows.infrastructure.auth.common.UUIDGenerator
import tk.mybatis.mapper.annotation.KeySql
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "users")
data class User(
        @Id
        @KeySql(genId = UUIDGenerator::class)
        val id: String,
        var username: String,
        var password: String,
        var isEnable: Boolean,
        var authorities: String,
        val createdAt: Long,
        var updatedAt: Long
)