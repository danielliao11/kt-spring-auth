package com.danielliaows.infrastructure.auth

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import tk.mybatis.spring.annotation.MapperScan

@MapperScan("com.danielliaows.infrastructure.auth.mapper")
@SpringBootApplication
class Application

fun main(args: Array<String>) {
  SpringApplication.run(Application::class.java, *args)
}