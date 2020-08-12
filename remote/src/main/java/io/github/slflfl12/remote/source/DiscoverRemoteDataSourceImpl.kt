package io.github.slflfl12.remote.source

import io.github.slflfl12.data.model.DiscoverMovieData
import io.github.slflfl12.data.remote.DiscoverRemoteDataSource
import io.github.slflfl12.remote.api.DiscoverApiService
import io.github.slflfl12.remote.mapper.DiscoverMovieRemoteMapper
import io.reactivex.Single

class DiscoverRemoteDataSourceImpl(
    private val discoverApiService: DiscoverApiService
) : DiscoverRemoteDataSource {

    override fun getDiscoverMovies(page: Int): Single<List<DiscoverMovieData>> {
        return discoverApiService.getDiscoverMovie(page)
            .map { it.discoverMovies.map(DiscoverMovieRemoteMapper::mapToData) }
    }
}