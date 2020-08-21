package io.github.slflfl12.presentation.mapper

import io.github.slflfl12.domain.model.KeywordModel
import io.github.slflfl12.presentation.model.KeywordPresentationModel

object KeywordPresentationMapper: PresentationMapper<KeywordPresentationModel, KeywordModel> {

    override fun mapToDomain(from: KeywordPresentationModel): KeywordModel {
        return KeywordModel(
            id = from.id,
            name= from.name
        )
    }

    override fun mapToPresentation(from: KeywordModel): KeywordPresentationModel {
        return KeywordPresentationModel(
            id = from.id,
            name= from.name
        )
    }
}