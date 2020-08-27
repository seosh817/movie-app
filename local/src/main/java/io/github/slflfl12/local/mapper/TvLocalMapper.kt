package io.github.slflfl12.local.mapper

import io.github.slflfl12.data.model.KeywordData
import io.github.slflfl12.data.model.ReviewData
import io.github.slflfl12.data.model.TvData
import io.github.slflfl12.data.model.VideoData
import io.github.slflfl12.local.model.KeywordEntity
import io.github.slflfl12.local.model.ReviewEntity
import io.github.slflfl12.local.model.TvEntity
import io.github.slflfl12.local.model.VideoEntity

object TvLocalMapper : LocalMapper<TvEntity, TvData> {
    override fun mapToData(from: TvEntity): TvData {
        return TvData(
            page = from.page,
            keywords = from.keywords?.map { KeywordData(id = it.id, name = it.name) },
            reviews = from.reviews?.map {
                ReviewData(
                    id = it.id,
                    author = it.author,
                    content = it.content,
                    url = it.url
                )
            },
            videos = from.videos?.map {
                VideoData(
                    id = it.id,
                    key = it.key,
                    name = it.name,
                    site = it.site,
                    size = it.size,
                    type = it.type
                )
            },
            backdropPath = from.backdropPath,
            firstAirDate = from.firstAirDate,
            genreIds = from.genreIds,
            id = from.id,
            name = from.name,
            originCountry = from.originCountry,
            originalLanguage = from.originalLanguage,
            originalName = from.originalName,
            overview = from.overview,
            popularity = from.popularity,
            posterPath = from.posterPath,
            voteCount = from.voteCount,
            voteAverage = from.voteAverage,
            favorite = from.favorite
        )
    }

    override fun mapToLocal(from: TvData): TvEntity {
        return TvEntity(
            page = from.page,
            keywords = from.keywords?.map { KeywordEntity(id = it.id, name = it.name) },
            reviews = from.reviews?.map {
                ReviewEntity(
                    id = it.id,
                    author = it.author,
                    content = it.content,
                    url = it.url
                )
            },
            videos = from.videos?.map {
                VideoEntity(
                    id = it.id,
                    key = it.key,
                    name = it.name,
                    site = it.site,
                    size = it.size,
                    type = it.type
                )
            },
            backdropPath = from.backdropPath,
            firstAirDate = from.firstAirDate,
            genreIds = from.genreIds,
            id = from.id,
            name = from.name,
            originCountry = from.originCountry,
            originalLanguage = from.originalLanguage,
            originalName = from.originalName,
            overview = from.overview,
            popularity = from.popularity,
            posterPath = from.posterPath,
            voteCount = from.voteCount,
            voteAverage = from.voteAverage,
            favorite = from.favorite
        )
    }
}