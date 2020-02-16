package com.danielliaows.infrastructure.auth.param

data class LoginParam(
        val username: String = "",
        val password: String = "",
        val type: String = "",
        val code: String = ""
)