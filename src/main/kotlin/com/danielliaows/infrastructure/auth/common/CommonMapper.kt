package com.danielliaows.invest.diary.common

import tk.mybatis.mapper.common.Mapper
import tk.mybatis.mapper.common.ids.SelectByIdsMapper

/**
 * Base mapper.
 *
 * @author <a href="http://github.com/saintdan">Liao Yifan</a>
 * @date 2020-01-12
 * @since JDK1.8
 */
interface CommonMapper<T> : SelectByIdsMapper<T>, Mapper<T>