package io.github.slflfl12.remote.mapper

import io.github.slflfl12.data.model.KeywordData
import io.github.slflfl12.remote.model.Keyword

object KeywordRemoteMapper: RemoteMapper<Keyword, KeywordData> {
    override fun mapToData(from: Keyword): KeywordData {
        return KeywordData(
            id = from.id,
            name = from.name
        )
    }

}