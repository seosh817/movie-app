package io.github.slflfl12.data.mapper

import io.github.slflfl12.data.model.ReviewData
import io.github.slflfl12.domain.model.ReviewModel

object ReviewDataMapper : DataMapper<ReviewData, ReviewModel> {

    override fun mapToDomain(from: ReviewData): ReviewModel {
        return ReviewModel(
            author = from.author,
            content = from.content,
            id = from.id,
            url = from.url
        )
    }

    override fun mapToData(from: ReviewModel): ReviewData {
        return ReviewData(
            author = from.author,
            content = from.content,
            id = from.id,
            url = from.url
        )
    }
}