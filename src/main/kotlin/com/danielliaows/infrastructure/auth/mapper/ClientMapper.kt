package com.danielliaows.infrastructure.auth.mapper

import com.danielliaows.infrastructure.auth.common.CommonMapper
import com.danielliaows.infrastructure.auth.model.Client
import com.danielliaows.infrastructure.auth.model.User
import org.apache.ibatis.annotations.Param

interface ClientMapper : CommonMapper<Client> {
    fun findByClientId(@Param("clientId") clientId: String): Client?
}