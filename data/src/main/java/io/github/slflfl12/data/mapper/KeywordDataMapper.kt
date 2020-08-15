package io.github.slflfl12.data.mapper

import io.github.slflfl12.data.model.KeywordData
import io.github.slflfl12.domain.model.KeywordModel

object KeywordDataMapper: DataMapper<KeywordData, KeywordModel> {

    override fun mapToData(from: KeywordModel): KeywordData {
        return KeywordData(
            id= from.id,
            name= from.name
        )
    }

    override fun mapToDomain(from: KeywordData): KeywordModel {
        return KeywordModel(
            id = from.id,
            name= from.name
        )
    }
}