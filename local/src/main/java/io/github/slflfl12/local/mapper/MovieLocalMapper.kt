package io.github.slflfl12.local.mapper

import io.github.slflfl12.data.model.MovieData
import io.github.slflfl12.local.model.MovieEntity

object MovieLocalMapper: LocalMapper<MovieEntity, MovieData> {

    override fun mapToData(from: MovieEntity): MovieData {
        return MovieData(
            page = from.page,
            adult = from.adult,
            backdropPath = from.backdrop_path,
            genreIds = from.genre_ids,
            id = from.id,
            originalLanguage = from.original_language,
            originalTitle = from.original_title,
            overview = from.overview,
            popularity = from.popularity,
            posterPath = from.poster_path,
            releaseDate = from.release_date,
            title = from.title,
            video = from.video,
            voteAverage = from.vote_average,
            voteCount = from.vote_count,
            favorite = from.favorite
        )
    }

    override fun mapToLocal(from: MovieData): MovieEntity {
        return MovieEntity(
            page = from.page,
            adult = from.adult,
            backdrop_path = from.backdropPath,
            genre_ids = from.genreIds,
            id = from.id,
            original_language = from.originalLanguage,
            original_title = from.originalTitle,
            overview = from.overview,
            popularity = from.popularity,
            poster_path = from.posterPath,
            release_date = from.releaseDate,
            title = from.title,
            video = from.video,
            vote_average = from.voteAverage,
            vote_count = from.voteCount,
            favorite = from.favorite
        )
    }


}