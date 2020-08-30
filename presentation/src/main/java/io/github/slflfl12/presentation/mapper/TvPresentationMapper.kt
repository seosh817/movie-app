package io.github.slflfl12.presentation.mapper

import io.github.slflfl12.domain.model.KeywordModel
import io.github.slflfl12.domain.model.ReviewModel
import io.github.slflfl12.domain.model.TvModel
import io.github.slflfl12.domain.model.VideoModel
import io.github.slflfl12.presentation.model.KeywordPresentationModel
import io.github.slflfl12.presentation.model.ReviewPresentationModel
import io.github.slflfl12.presentation.model.TvPresentationModel
import io.github.slflfl12.presentation.model.VideoPresentationModel

object TvPresentationMapper: PresentationMapper<TvPresentationModel, TvModel> {
    override fun mapToDomain(from: TvPresentationModel): TvModel {
       return TvModel(
           page = from.page,
           keywords = from.keywords?.map(KeywordPresentationMapper::mapToDomain),
           reviews = from.reviews?.map(ReviewPresentationMapper::mapToDomain),
           videos = from.videos?.map(VideoPresentationMapper::mapToDomain),
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

    override fun mapToPresentation(from: TvModel): TvPresentationModel {
        return TvPresentationModel(
            page = from.page,
            keywords = from.keywords?.map(KeywordPresentationMapper::mapToPresentation),
            reviews = from.reviews?.map(ReviewPresentationMapper::mapToPresentation),
            videos = from.videos?.map(VideoPresentationMapper::mapToPresentation),
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