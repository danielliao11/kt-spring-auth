package com.danielliaows.infrastructure.auth.domain

import com.danielliaows.infrastructure.auth.common.CommonDomain
import com.danielliaows.infrastructure.auth.mapper.ClientMapper
import com.danielliaows.infrastructure.auth.model.Client
import org.springframework.stereotype.Service

@Service
class ClientDomain(
    private val clientMapper: ClientMapper
) : CommonDomain<ClientMapper, Client>(clientMapper)