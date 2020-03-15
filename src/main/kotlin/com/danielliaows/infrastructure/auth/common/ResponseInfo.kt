package com.danielliaows.infrastructure.auth.common

import javax.persistence.Transient

open class ResponseInfo(

    @Transient
    var code: String? = null,

    @Transient
    val msg: String? = null
)