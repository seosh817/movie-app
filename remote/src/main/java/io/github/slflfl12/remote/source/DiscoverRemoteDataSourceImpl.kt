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
            .map {
                it.discoverMovies.map { from ->
                    MovieData(
                        page = from.page,
                        adult = from.adult ?: false,
                        backdropPath = from.backdropPath ?: "",
                        genreIds = from.genreIds ?: listOf(),
                        id = from.id,
                        originalLanguage = from.originalLanguage ?: "",
                        originalTitle = from.originalTitle ?: "",
                        overview = from.overview ?: "",
                        popularity = from.popularity ?: 0.0,
                        posterPath = from.posterPath ?: "",
                        releaseDate = from.releaseDate ?: "",
                        title = from.title ?: "",
                        video = from.video ?: false,
                        voteAverage = from.voteAverage ?: 0.0,
                        voteCount = from.voteCount ?: 0,
                        favorite = false
                    )
                }

            }
    }
}