package com.danielliaows.infrastructure.auth.controller

import com.danielliaows.infrastructure.auth.common.ResponseInfo
import com.danielliaows.infrastructure.auth.handler.LoginHandler
import com.danielliaows.infrastructure.auth.param.LoginParam
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException
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
        return try {
            ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(loginHandler.login(request, param))
        } catch (e: Exception) {
            var status = HttpStatus.UNPROCESSABLE_ENTITY
            var code = "AU_UNKNOWN_ERROR"
            when (e) {
                is UnapprovedClientAuthenticationException -> code = "AU_INVALID_CLIENT"
                is BadCredentialsException -> code = "AU_BAD_CREDENTIAL"
                is UsernameNotFoundException -> code = "AU_USERNAME_NOT_FOUND"
                else -> status = HttpStatus.INTERNAL_SERVER_ERROR
            }
            return ResponseEntity.status(status).body(ResponseInfo(code, e.localizedMessage))
        }
    }
}