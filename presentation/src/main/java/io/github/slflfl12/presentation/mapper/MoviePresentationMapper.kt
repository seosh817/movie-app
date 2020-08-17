package io.github.slflfl12.presentation.mapper

import io.github.slflfl12.domain.model.MovieModel
import io.github.slflfl12.presentation.model.MoviePresentationModel

object MoviePresentationMapper: PresentationMapper<MoviePresentationModel, MovieModel> {
    override fun mapToDomain(from: MoviePresentationModel): MovieModel {
        return MovieModel(
            page = from.page,
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