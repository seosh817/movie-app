package io.github.slflfl12.presentation.mapper

import io.github.slflfl12.domain.model.KeywordModel
import io.github.slflfl12.domain.model.MovieModel
import io.github.slflfl12.domain.model.ReviewModel
import io.github.slflfl12.domain.model.VideoModel
import io.github.slflfl12.presentation.model.KeywordPresentationModel
import io.github.slflfl12.presentation.model.MoviePresentationModel
import io.github.slflfl12.presentation.model.ReviewPresentationModel
import io.github.slflfl12.presentation.model.VideoPresentationModel

object MoviePresentationMapper: PresentationMapper<MoviePresentationModel, MovieModel> {
    override fun mapToDomain(from: MoviePresentationModel): MovieModel {
        return MovieModel(
            page = from.page,
            keywords = from.keywords.map { KeywordModel(id = it.id, name = it.name) },
            reviews = from.reviews.map {
                ReviewModel(
                    id = it.id,
                    author = it.author,
                    content = it.content,
                    url = it.url
                )
            },
            videos = from.videos.map {
                VideoModel(
                    id = it.id,
                    key = it.key,
                    name = it.name,
                    site = it.site,
                    size = it.size,
                    type = it.type
                )
            },
            adult = from.adult,
            backdropPath = from.backdropPath,
            genreIds = from.genreIds,
            id = from.id,
            originalLanguage = from.originalLanguage,
            originalTitle = from.originalTitle,
            overview = from.overview,
            popularity = from.popularity,
            posterPath = from.posterPath,
            releaseDate = from.releaseDate,
            title = from.title,
            video = from.video,
            voteAverage = from.voteAverage,
            voteCount = from.voteCount,
            favorite = from.favorite
        )
    }

    override fun mapToPresentation(from: MovieModel): MoviePresentationModel {
        return MoviePresentationModel(
            page = from.page,
            keywords = from.keywords.map { KeywordPresentationModel(id = it.id, name = it.name) },
            reviews = from.reviews.map {
                ReviewPresentationModel(
                    id = it.id,
                    author = it.author,
                    content = it.content,
                    url = it.url
                )
            },
            videos = from.videos.map {
                VideoPresentationModel(
                    id = it.id,
                    key = it.key,
                    name = it.name,
                    site = it.site,
                    size = it.size,
                    type = it.type
                )
            },
            adult = from.adult,
            backdropPath = from.backdropPath,
            genreIds = from.genreIds,
            id = from.id,
            originalLanguage = from.originalLanguage,
            originalTitle = from.originalTitle,
            overview = from.overview,
            popularity = from.popularity,
            posterPath = from.posterPath,
            releaseDate = from.releaseDate,
            title = from.title,
            video = from.video,
            voteAverage = from.voteAverage,
            voteCount = from.voteCount,
            favorite = from.favorite
        )
    }
}