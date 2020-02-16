package com.danielliaows.invest.diary.common

import tk.mybatis.mapper.genid.GenId
import java.util.*

/**
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 2020-01-12
 * @since JDK1.8
 */
class UUIDGenerator : GenId<String> {
    override fun genId(s: String?, s1: String?): String? {
        return UUID.randomUUID().toString().replace("-".toRegex(), "")
    }
}