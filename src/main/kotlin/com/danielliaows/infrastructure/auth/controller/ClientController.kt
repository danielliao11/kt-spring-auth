package com.danielliaows.infrastructure.auth.controller

import com.danielliaows.infrastructure.auth.common.CommonInfo
import com.danielliaows.infrastructure.auth.common.Query
import com.danielliaows.infrastructure.auth.domain.ClientDomain
import com.danielliaows.infrastructure.auth.model.Client
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("clients")
class ClientController(
        private val domain: ClientDomain
) {

    @PostMapping
    fun create(@RequestBody model: Client): ResponseEntity<CommonInfo> {
        val result = domain.create(model)
        if (result < 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(model)
    }

    @GetMapping
    fun page(@RequestParam params: Map<String, Any>): ResponseEntity<Any> = ResponseEntity.ok().body(domain.page(Query.parse(params)))

    @PatchMapping("{id}")
    fun update(@RequestBody model: Client, @PathVariable id: String): ResponseEntity<CommonInfo> {
        model.id = id
        val result = domain.updateByPrimaryKeySelective(model)
        if (result < 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
        return ResponseEntity.ok().body(model)
    }
}