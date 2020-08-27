package io.github.slflfl12.remote.source

import io.github.slflfl12.data.model.MovieData
import io.github.slflfl12.data.model.TvData
import io.github.slflfl12.data.remote.DiscoverRemoteDataSource
import io.github.slflfl12.remote.api.DiscoverApiService
import io.github.slflfl12.remote.mapper.MovieRemoteMapper
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class DiscoverRemoteDataSourceImpl(
    private val discoverApiService: DiscoverApiService
) : DiscoverRemoteDataSource {

    override fun getDiscoverMovies(page: Int): Single<List<MovieData>> {
        return discoverApiService.getDiscoverMovie(page)
            .map {
                it.discoverMovies.map { from ->
                    MovieData(
                        page = page,
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
            }.subscribeOn(Schedulers.io())
    }

    override fun getDiscoverTvs(page: Int): Single<List<TvData>> {
        return discoverApiService.getDiscoverTv(page)
            .map {
                it.discoverTvs.map { from ->
                    TvData(
                        page = page,
                        backdropPath = from.backdropPath,
                        firstAirDate = from.firstAirDate,
                        genreIds = from.genreIds,
                        id = from.id,
                        name= from.name,
                        originCountry = from.originCountry,
                        originalLanguage = from.originalLanguage,
                        originalName = from.originalName,
                        overview = from.overview,
                        popularity = from.popularity,
                        posterPath = from.posterPath,
                        voteCount = from.voteCount,
                        voteAverage = from.voteAverage,
                        favorite = false
                    )
                }
            }
    }
}