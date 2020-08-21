package io.github.slflfl12.presentation.mapper

import io.github.slflfl12.domain.model.VideoModel
import io.github.slflfl12.presentation.model.VideoPresentationModel

object VideoPresentationMapper: PresentationMapper<VideoPresentationModel, VideoModel> {

    override fun mapToDomain(from: VideoPresentationModel): VideoModel {
        return VideoModel(
            id=from.id,
            key=from.key,
            name=from.name,
            site=from.site,
            size=from.size,
            type=from.type
        )
    }

    override fun mapToPresentation(from: VideoModel): VideoPresentationModel {
        return VideoPresentationModel(
            id=from.id,
            key=from.key,
            name=from.name,
            site=from.site,
            size=from.size,
            type=from.type
        )
    }
}