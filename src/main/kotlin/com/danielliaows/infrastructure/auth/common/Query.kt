package com.danielliaows.infrastructure.auth.common

import org.apache.commons.lang3.StringUtils

data class Query(
    var page: Int = 1,
    var limit: Int = 20,
    var sort: String = "",
    var spec: String = "",
    var params: HashMap<String, Any> = HashMap()
) {
    companion object {
        fun parse(src: Map<String, Any>): Query {
            val query = Query()
            query.params.putAll(src)
            if (null != query.params["page"]) {
                query.page = query.params["page"].toString().toInt()
            }
            if (null != query.params["limit"]) {
                query.limit = query.params["limit"].toString().toInt()
            }
            if (null != query.params["sort"] && StringUtils.isNotBlank(query.params["sort"].toString())) {
                query.sort = query.params["sort"].toString()
            }
            if (null != query.params["spec"] && StringUtils.isNotBlank(query.params["spec"].toString())) {
                query.spec = query.params["spec"].toString()
            }
            query.params.remove("page")
            query.params.remove("limit")
            query.params.remove("sort")
            query.params.remove("spec")

            return query
        }
    }
}