package com.danielliaows.infrastructure.auth.common

import tk.mybatis.mapper.genid.GenId
import java.util.*

class UUIDGenerator : GenId<String> {
    override fun genId(s: String?, s1: String?): String? {
        return UUID.randomUUID().toString().replace("-".toRegex(), "")
    }
}