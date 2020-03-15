package com.danielliaows.infrastructure.auth.model

import com.danielliaows.infrastructure.auth.common.CommonInfo
import com.danielliaows.infrastructure.auth.common.UUIDGenerator
import tk.mybatis.mapper.annotation.KeySql
import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "auth_types")
data class AuthType(
        @Id
        @KeySql(genId = UUIDGenerator::class)
        var id: String? = null,

        @Column(name = "user_id")
        val userId: String? = null,

        @Column(name = "type")
        var type: String? = null,

        @Column(name = "content")
        var content: String? = null
) : CommonInfo()