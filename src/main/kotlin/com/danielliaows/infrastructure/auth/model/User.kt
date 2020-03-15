package com.danielliaows.infrastructure.auth.model

import com.danielliaows.infrastructure.auth.common.CommonInfo
import com.danielliaows.infrastructure.auth.common.UUIDGenerator
import tk.mybatis.mapper.annotation.KeySql
import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Transient

@Table(name = "users")
data class User(
        @Id
        @KeySql(genId = UUIDGenerator::class)
        val id: String? = null,

        @Column(name = "username")
        var username: String? = null,

        @Column(name = "password")
        var password: String? = null,

        @Column(name = "is_enable")
        var isEnable: Boolean? = null,

        @Transient
        var authorities: String? = null
) : CommonInfo()