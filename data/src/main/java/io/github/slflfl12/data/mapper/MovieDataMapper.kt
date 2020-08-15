package io.github.slflfl12.data.mapper

import io.github.slflfl12.data.model.MovieData
import io.github.slflfl12.domain.model.MovieModel

object MovieDataMapper: DataMapper<MovieData, MovieModel> {
    override fun mapToDomain(from: MovieData): MovieModel {
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

    override fun mapToData(from: MovieModel): MovieData {
        return MovieData(
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