package io.github.slflfl12.data.repository

import io.github.slflfl12.data.mapper.MovieDataMapper
import io.github.slflfl12.data.remote.DiscoverRemoteDataSource
import io.github.slflfl12.domain.model.MovieModel
import io.github.slflfl12.domain.repository.DiscoverRepository
import io.reactivex.Single

class DiscoverRepositoryImpl(
    private val discoverRemoteDataSource: DiscoverRemoteDataSource
) : DiscoverRepository {
    override fun getDiscoverMovies(page: Int): Single<List<MovieModel>> {
        return discoverRemoteDataSource.getDiscoverMovies(page)
            .map { it.map(MovieDataMapper::mapToDomain) }
    }
}