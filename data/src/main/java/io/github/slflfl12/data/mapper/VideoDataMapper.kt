package io.github.slflfl12.data.mapper

import io.github.slflfl12.data.model.VideoData
import io.github.slflfl12.domain.model.VideoModel

object VideoDataMapper : DataMapper<VideoData, VideoModel> {
    override fun mapToData(from: VideoModel): VideoData {
        return VideoData(
            id = from.id,
            key = from.key,
            name = from.name,
            site = from.site,
            size = from.size,
            type = from.type
        )
    }

    override fun mapToDomain(from: VideoData): VideoModel {
        return VideoModel(
            id = from.id,
            key = from.key,
            name = from.name,
            site = from.site,
            size = from.size,
            type = from.type
        )
    }
}