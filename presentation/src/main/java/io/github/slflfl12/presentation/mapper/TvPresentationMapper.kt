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

    override fun mapToPresentation(from: TvModel): TvPresentationModel {
        return TvPresentationModel(
            page = from.page,
            keywords = from.keywords?.map { KeywordPresentationModel(id = it.id, name = it.name) },
            reviews = from.reviews?.map {
                ReviewPresentationModel(
                    id = it.id,
                    author = it.author,
                    content = it.content,
                    url = it.url
                )
            },
            videos = from.videos?.map {
                VideoPresentationModel(
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