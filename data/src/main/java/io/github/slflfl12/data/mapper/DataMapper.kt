package io.github.slflfl12.data.mapper

import io.github.slflfl12.data.model.Data
import io.github.slflfl12.domain.model.Model

interface DataMapper <in D:Data, out M: Model> {
    fun mapToDomain(from: D): M
}