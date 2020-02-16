package com.danielliaows.infrastructure.auth.tool

import kotlin.random.Random

class RandomCodeGenerator {
    companion object {
        private val numbers: CharArray = "0123456789".toCharArray()
        private val characters: CharArray = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()

        fun random(length: Int, number: Boolean, character: Boolean) {
            val source: CharArray = "".toCharArray()
            if (number) {
                source.plus(numbers)
            }
            if (character) {
                source.plus(characters)
            }
            val result = StringBuilder()
            for (i in 0..length) {
                result.append(source[Random.nextInt()])
            }
        }
    }
}