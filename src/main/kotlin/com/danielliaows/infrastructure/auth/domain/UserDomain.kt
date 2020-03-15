package com.danielliaows.infrastructure.auth.domain

import com.danielliaows.infrastructure.auth.common.CommonDomain
import com.danielliaows.infrastructure.auth.mapper.UserMapper
import com.danielliaows.infrastructure.auth.model.User
import org.springframework.stereotype.Service

@Service
class UserDomain(
    private val userMapper: UserMapper
) : CommonDomain<UserMapper, User>(userMapper)