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
            keywords = from.keywords?.map(KeywordDataMapper::mapToDomain),
            reviews = from.reviews?.map(ReviewDataMapper::mapToDomain),
            videos = from.videos?.map(VideoDataMapper::mapToDomain),
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
            keywords = from.keywords?.map(KeywordDataMapper::mapToData),
            reviews = from.reviews?.map(ReviewDataMapper::mapToData),
            videos = from.videos?.map(VideoDataMapper::mapToData),
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