package com.danielliaows.infrastructure.auth.controller

import com.danielliaows.infrastructure.auth.common.ResponseInfo
import com.danielliaows.infrastructure.auth.handler.LogoutHandler
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/open/logout")
class LogoutController(
        private val httpServletRequest: HttpServletRequest,
        private val logoutHandler: LogoutHandler
) {

    @PostMapping
    fun logout(): ResponseEntity<Any> {
        try {
            logoutHandler.logout(httpServletRequest)
        } catch (e: InvalidTokenException) {
            ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ResponseInfo("", e.localizedMessage))
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseInfo("", e.localizedMessage))
        }
        return ResponseEntity.ok().build()
    }
}