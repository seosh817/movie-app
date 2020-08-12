package io.github.slflfl12.remote.mapper

import io.github.slflfl12.data.model.DiscoverMovieData
import io.github.slflfl12.remote.model.DiscoverMovie

object DiscoverMovieRemoteMapper:RemoteMapper<DiscoverMovie, DiscoverMovieData> {
    override fun mapToData(from: DiscoverMovie): DiscoverMovieData {
        return DiscoverMovieData(
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
            voteCount = from.voteCount
        )
    }
}