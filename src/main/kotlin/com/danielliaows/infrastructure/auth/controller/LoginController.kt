package com.danielliaows.infrastructure.auth.controller

import com.danielliaows.infrastructure.auth.handler.LoginHandler
import com.danielliaows.infrastructure.auth.param.LoginParam
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("open/login")
class LoginController(
        private val request: HttpServletRequest,
        private val loginHandler: LoginHandler
) {

    @PostMapping
    fun login(@RequestBody param: LoginParam): ResponseEntity<Any> {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(loginHandler.login(request, param))
    }
}