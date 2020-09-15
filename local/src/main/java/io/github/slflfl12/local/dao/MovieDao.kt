package io.github.slflfl12.local.dao

import androidx.room.*
import io.github.slflfl12.local.model.MovieEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(movies: List<MovieEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieEntity): Completable

    @Update
    fun updateMovie(movie: MovieEntity) : Completable

    @Query("SELECT * FROM movie")
    fun getAllMovies(): Single<MovieEntity>

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovie(id: Int): Single<MovieEntity>

    @Query("SELECT * FROM movie WHERE page = :page")
    fun getMovieList(page: Int): Single<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE favorite = '1'")
    fun getFavoriteMovieList(): Single<List<MovieEntity>>
}