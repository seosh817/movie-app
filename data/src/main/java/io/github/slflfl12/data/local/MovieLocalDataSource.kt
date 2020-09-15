package io.github.slflfl12.data.local

import io.github.slflfl12.data.model.MovieData
import io.reactivex.Completable
import io.reactivex.Single

interface MovieLocalDataSource {

    fun insertMovieList(movieDataList: List<MovieData>): Completable

    fun insertMovie(movie: MovieData): Completable

    fun updateMovie(movie: MovieData): Completable

    fun getMovie(id: Int): Single<MovieData>

    fun getLocalMovieList(page: Int): Single<List<MovieData>>
}