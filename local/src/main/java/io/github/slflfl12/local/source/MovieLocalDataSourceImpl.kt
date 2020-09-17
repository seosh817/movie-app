package io.github.slflfl12.local.source

import io.github.slflfl12.data.local.MovieLocalDataSource
import io.github.slflfl12.data.model.MovieData
import io.github.slflfl12.local.dao.MovieDao
import io.github.slflfl12.local.mapper.MovieLocalMapper
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class MovieLocalDataSourceImpl(
    private val movieDao: MovieDao
) : MovieLocalDataSource {
    override fun insertMovieList(movieDataList: List<MovieData>): Completable =
        movieDao.insertMovieList(movieDataList.map(MovieLocalMapper::mapToLocal))
            .subscribeOn(Schedulers.io())

    override fun insertMovie(movie: MovieData): Completable =
        movieDao.insertMovie(MovieLocalMapper.mapToLocal(movie))

    override fun updateMovie(movie: MovieData): Completable =
        movieDao.updateMovie(MovieLocalMapper.mapToLocal(movie))
            .subscribeOn(Schedulers.io())

    override fun getMovie(id: Int): Single<MovieData> =
        movieDao.getMovie(id).map(MovieLocalMapper::mapToData)
            .subscribeOn(Schedulers.io())


    override fun getLocalMovieList(page: Int): Single<List<MovieData>> =
        movieDao.getMovieList(page).map { it.map(MovieLocalMapper::mapToData) }
            .subscribeOn(Schedulers.io())

    override fun getFavoriteMovieList(): Single<List<MovieData>> =
        movieDao.getFavoriteMovieList().map { it.map(MovieLocalMapper::mapToData) }
            .subscribeOn(Schedulers.io())
}