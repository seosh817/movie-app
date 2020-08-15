package io.github.slflfl12.remote.source

import io.github.slflfl12.data.model.MovieData
import io.github.slflfl12.data.remote.DiscoverRemoteDataSource
import io.github.slflfl12.remote.api.DiscoverApiService
import io.github.slflfl12.remote.mapper.MovieRemoteMapper
import io.reactivex.Single

class DiscoverRemoteDataSourceImpl(
    private val discoverApiService: DiscoverApiService
) : DiscoverRemoteDataSource {

    override fun getDiscoverMovies(page: Int): Single<List<MovieData>> {
        return discoverApiService.getDiscoverMovie(page)
            .map { it.discoverMovies.map {
                MovieData(
                    page = page,
                    adult = it.adult,
                    backdropPath = it.backdropPath,
                    genreIds = it.genreIds,
                    id = it.id,
                    originalLanguage = it.originalLanguage,
                    originalTitle = it.originalTitle,
                    overview = it.overview,
                    popularity = it.popularity,
                    posterPath = it.posterPath,
                    releaseDate = it.releaseDate,
                    title = it.title,
                    video = it.video,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount,
                    favorite = false
                )
            }

             }
    }
}