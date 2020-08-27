package io.github.slflfl12.data.remote

import io.github.slflfl12.data.model.MovieData
import io.github.slflfl12.data.model.TvData
import io.reactivex.Single

interface DiscoverRemoteDataSource {

    fun getDiscoverMovies(page: Int): Single<List<MovieData>>

    fun getDiscoverTvs(page: Int): Single<List<TvData>>

}