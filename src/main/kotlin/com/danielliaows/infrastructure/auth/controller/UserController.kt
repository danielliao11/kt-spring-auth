package com.danielliaows.infrastructure.auth.controller

import com.danielliaows.infrastructure.auth.common.CommonInfo
import com.danielliaows.infrastructure.auth.common.Query
import com.danielliaows.infrastructure.auth.domain.UserDomain
import com.danielliaows.infrastructure.auth.handler.UserInfoHandler
import com.danielliaows.infrastructure.auth.model.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("users")
class UserController(
        private val userInfoHandler: UserInfoHandler,
        private val domain: UserDomain
) {

    @GetMapping
    fun getDetails(principal: Principal?): ResponseEntity<Any> = ResponseEntity.ok(userInfoHandler.getDetails(principal))

    @PostMapping
    fun create(@RequestBody model: User): ResponseEntity<CommonInfo> {
        val result = domain.create(model)
        if (result < 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(model)
    }

    @GetMapping
    fun page(@RequestParam params: Map<String, Any>): ResponseEntity<Any> = ResponseEntity.ok().body(domain.page(Query.parse(params)))

    @PatchMapping("{id}")
    fun update(@RequestBody model: User, @PathVariable id: String): ResponseEntity<CommonInfo> {
        model.id = id
        val result = domain.updateByPrimaryKeySelective(model)
        if (result < 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
        return ResponseEntity.ok().body(model)
    }
}