package io.github.slflfl12.domain.repository

import io.github.slflfl12.domain.model.MovieModel
import io.github.slflfl12.domain.model.TvModel
import io.reactivex.Single

interface DiscoverRepository {

    fun getDiscoverMovies(page: Int): Single<List<MovieModel>>

    fun getDiscoverTvs(page: Int): Single<List<TvModel>>
}