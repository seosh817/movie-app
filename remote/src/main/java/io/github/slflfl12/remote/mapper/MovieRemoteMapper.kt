package io.github.slflfl12.remote.mapper

import io.github.slflfl12.data.model.MovieData
import io.github.slflfl12.remote.model.DiscoverMovie

object MovieRemoteMapper {
    fun mapToData(from: DiscoverMovie, page: Int): MovieData {
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
            favorite = false
        )
    }
}