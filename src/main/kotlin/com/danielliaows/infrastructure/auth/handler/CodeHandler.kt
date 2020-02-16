package com.danielliaows.infrastructure.auth.handler

import com.danielliaows.infrastructure.auth.common.RandomCodeGenerator
import org.springframework.stereotype.Service

@Service
class CodeHandler {

    fun generate(): String {
        val code = RandomCodeGenerator.random(6, true, true)
        println(code)
        return code
    }

    fun verify(key: String, value: String): Boolean {
        return value == "123456"
    }
}