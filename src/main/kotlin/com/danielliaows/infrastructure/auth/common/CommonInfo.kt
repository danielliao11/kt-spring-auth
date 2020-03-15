package com.danielliaows.infrastructure.auth.common

import javax.persistence.Column

open class CommonInfo(

    @Column(name = "created_at")
    var createdAt: Long? = null,

    @Column(name = "created_by")
    var createdBy: String? = null,

    @Column(name = "updated_at")
    var updatedAt: Long? = null,

    @Column(name = "updated_by")
    var updatedBy: String? = null
) : ResponseInfo()