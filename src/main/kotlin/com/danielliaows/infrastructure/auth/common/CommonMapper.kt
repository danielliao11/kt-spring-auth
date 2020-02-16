package com.danielliaows.infrastructure.auth.common

import tk.mybatis.mapper.common.Mapper
import tk.mybatis.mapper.common.ids.SelectByIdsMapper

interface CommonMapper<T> : SelectByIdsMapper<T>, Mapper<T>