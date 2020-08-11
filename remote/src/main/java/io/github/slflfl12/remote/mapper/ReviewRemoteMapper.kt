package io.github.slflfl12.remote.mapper

import io.github.slflfl12.data.model.ReviewData
import io.github.slflfl12.remote.model.Review

object ReviewRemoteMapper : RemoteMapper<Review, ReviewData> {
    override fun mapToData(from: Review): ReviewData {
        return ReviewData(
            author = from.author,
            content = from.content,
            id = from.content,
            url = from.url
        )
    }
}