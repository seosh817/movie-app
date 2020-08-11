package io.github.slflfl12.remote.mapper

import io.github.slflfl12.data.model.Data
import io.github.slflfl12.remote.model.Response

interface RemoteMapper<in R: Response, out D: Data> {
    fun mapToData(from: R): D
}