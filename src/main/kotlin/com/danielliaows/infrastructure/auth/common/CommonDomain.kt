package com.danielliaows.infrastructure.auth.common

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import org.apache.commons.lang3.StringUtils
import tk.mybatis.mapper.entity.Example
import java.lang.reflect.ParameterizedType
abstract class CommonDomain<M: CommonMapper<T>, T: CommonInfo>(
    protected val mapper: M
) {
    fun create(record: T): Int {
        val now = System.currentTimeMillis()
        record.createdAt = now
        record.updatedAt = now
        return mapper.insertSelective(record)
    }

    fun findOne(record: T): T = mapper.selectOne(record)

    fun findByPrimaryKey(id: Any): T = mapper.selectByPrimaryKey(id)

    fun findAll(): List<T> = mapper.selectAll()

    fun selectByExample(example: Any): List<T> = mapper.selectByExample(example)

    fun page(query: Query = Query()): PageInfo<T> {
        val clazz = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<T>
        val example = Example(clazz)
        query2criteria(query, example)
        val result = PageHelper.startPage<T>(query.page, query.limit)
        val list = this.selectByExample(example)
        return PageInfo(list, result.total.toInt())
    }

    fun updateByPrimaryKey(record: T): Int {
        record.updatedAt = System.currentTimeMillis()
        return mapper.updateByPrimaryKey(record)
    }

    fun updateByPrimaryKeySelective(record: T): Int {
        record.updatedAt = System.currentTimeMillis()
        return mapper.updateByPrimaryKeySelective(record)
    }

    fun deleteByPrimaryKey(id: Any) = mapper.deleteByPrimaryKey(id)

    fun delete(record: T) = mapper.delete(record)

    fun query2criteria(query: Query, example: Example) {
        if (query.params.entries.size == 0 && StringUtils.isBlank(query.spec) && StringUtils.isBlank(query.sort)) {
            return
        }
        val criteria = example.createCriteria()

        // Handle order by statement.
        // e.g., sort = createdAt:[desc],id:[asc].
        if (StringUtils.isNotBlank(query.sort) && Keywords.hasKeywords(query.sort)) {
            query.sort.split(",").forEach {
                val sortArray = it.split(":")
                if ("desc" == sortArray[1].trim().toLowerCase()) {
                    example.orderBy(sortArray[0].trim()).desc()
                } else if("asc" == sortArray[1].trim().toLowerCase()) {
                    example.orderBy(sortArray[0].trim()).asc()
                }
            }
        }

        // Handle compare statement.
        // e.g., spec = createdAt:[gte]:1577808000000,createdAt:[lte]:1609430400000
        if (StringUtils.isNotBlank(query.spec) && Keywords.hasKeywords(query.spec)) {
            query.spec.split(",").forEach {
                val specArray = it.split(":")
                when(specArray[1]) {
                    Keywords.EQUAL_TO.keywords -> criteria.andEqualTo(specArray[0], specArray[2])
                    Keywords.NOT_EQUAL_TO.keywords -> criteria.andNotEqualTo(specArray[0], specArray[2])
                    Keywords.LESS_THAN.keywords -> criteria.andLessThan(specArray[0], specArray[2])
                    Keywords.LESS_THAN_OR_EQUAL_TO.keywords -> criteria.andLessThanOrEqualTo(specArray[0], specArray[2])
                    Keywords.GREATER_THAN.keywords -> criteria.andGreaterThan(specArray[0], specArray[2])
                    Keywords.GREATER_THAN_OR_EQUAL_TO.keywords -> criteria.andGreaterThanOrEqualTo(specArray[0], specArray[2])
                }
            }
        }

        // Handle other query statement
        query.params.forEach { (k, v) -> criteria.andLike(k, "%$v%") }
    }
}

