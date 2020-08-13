package io.github.slflfl12.local.source

import io.github.slflfl12.data.local.MovieLocalDataSource
import io.github.slflfl12.data.model.MovieData
import io.github.slflfl12.local.dao.MovieDao
import io.github.slflfl12.local.mapper.MovieLocalMapper
import io.reactivex.Completable
import io.reactivex.Single

class MovieLocalDataSourceImpl(
    private val movieDao: MovieDao
): MovieLocalDataSource {
    override fun insertMovieList(movieDataList: List<MovieData>): Completable {
        return movieDao.insertMovieList(movieDataList.map(MovieLocalMapper::mapToLocal))
    }

    override fun updateMovie(movie: MovieData): Completable {
        return movieDao.updateMovie(MovieLocalMapper.mapToLocal(movie))
    }

    override fun getMovie(id: Int): Single<MovieData> {
        return movieDao.getMovie(id).map(MovieLocalMapper::mapToData)
    }

    override fun getLocalMovieList(page: Int): Single<List<MovieData>> {
        return movieDao.getMovieList(page).map {
            it.map(MovieLocalMapper::mapToData)
        }
    }
}