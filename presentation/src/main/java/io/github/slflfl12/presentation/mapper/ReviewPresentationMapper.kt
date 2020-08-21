package io.github.slflfl12.presentation.mapper

import io.github.slflfl12.domain.model.ReviewModel
import io.github.slflfl12.presentation.model.ReviewPresentationModel

object ReviewPresentationMapper : PresentationMapper<ReviewPresentationModel, ReviewModel> {

    override fun mapToDomain(from: ReviewPresentationModel): ReviewModel {
        return ReviewModel(
            author = from.author,
            content = from.content,
            id = from.id,
            url = from.url
        )
    }

    override fun mapToPresentation(from: ReviewModel): ReviewPresentationModel {
        return ReviewPresentationModel(
            id = from.id,
            author = from.author,
            content = from.content,
            url = from.url
        )
    }
}