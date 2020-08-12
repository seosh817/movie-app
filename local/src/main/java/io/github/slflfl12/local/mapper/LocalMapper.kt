package io.github.slflfl12.local.mapper

import io.github.slflfl12.data.model.Data
import io.github.slflfl12.local.model.Entity

interface LocalMapper<E: Entity, D: Data> {
    fun mapToData(from: E): D

    fun mapToLocal(from: D): E
}