package com.danielliaows.infrastructure.auth.common

enum class Keywords(val keywords: String) {

    ASC("[asc]"),
    DESC("[desc]"),
    EQUAL_TO("[eq]"),
    NOT_EQUAL_TO("[ne]"),
    LESS_THAN("[lt]"),
    LESS_THAN_OR_EQUAL_TO("[lte]"),
    GREATER_THAN("[gt]"),
    GREATER_THAN_OR_EQUAL_TO("[gte]");

    companion object {
        fun hasKeywords(src: String): Boolean = Keywords.values().any {  src.contains(it.keywords) }
        fun parse(src: String): Keywords = Keywords.values().first { it.keywords == src }
    }
}