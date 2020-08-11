package io.github.slflfl12.remote.mapper

import io.github.slflfl12.data.model.VideoData
import io.github.slflfl12.remote.model.Video

object VideoRemoteMapper : RemoteMapper<Video, VideoData> {
    override fun mapToData(from: Video): VideoData {
        return VideoData(
            id = from.id,
            key = from.key,
            name = from.name,
            site = from.site,
            size = from.size,
            type = from.type
        )
    }
}