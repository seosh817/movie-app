package io.github.slflfl12.data.mapper

import io.github.slflfl12.data.model.KeywordData
import io.github.slflfl12.data.model.ReviewData
import io.github.slflfl12.data.model.TvData
import io.github.slflfl12.data.model.VideoData
import io.github.slflfl12.domain.model.KeywordModel
import io.github.slflfl12.domain.model.ReviewModel
import io.github.slflfl12.domain.model.TvModel
import io.github.slflfl12.domain.model.VideoModel

object TvDataMapper : DataMapper<TvData, TvModel> {

    override fun mapToDomain(from: TvData): TvModel {
        return TvModel(
            page = from.page,
            keywords = from.keywords?.map { KeywordModel(id = it.id, name = it.name) },
            reviews = from.reviews?.map {
                ReviewModel(
                    id = it.id,
                    author = it.author,
                    content = it.content,
                    url = it.url
                )
            },
            videos = from.videos?.map {
                VideoModel(
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

    override fun mapToData(from: TvModel): TvData {
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
}