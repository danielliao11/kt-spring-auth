package com.danielliaows.infrastructure.auth.mapper

import com.danielliaows.infrastructure.auth.common.CommonMapper
import com.danielliaows.infrastructure.auth.model.User
import org.apache.ibatis.annotations.Param

interface UserMapper : CommonMapper<User> {
    fun findByUsername(@Param("username") username: String): User?
}