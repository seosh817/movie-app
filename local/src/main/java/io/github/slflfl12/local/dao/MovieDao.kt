package io.github.slflfl12.local.dao

import androidx.room.*
import io.github.slflfl12.local.model.MovieEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(movies: List<MovieEntity>): Completable

    @Update
    fun updateMovie(movie: MovieEntity) : Completable

    @Query("SELECT * FROM MovieEntity WHERE id = :id")
    fun getMovie(id: Int): Single<MovieEntity>

    @Query("SELECT * FROM MovieEntity WHERE page = :page")
    fun getMovieList(page: Int): Single<List<MovieEntity>>

    @Query("SELECT * FROM MovieEntity WHERE favorite = '1'")
    fun getFavoriteMovieList(): Single<List<MovieEntity>>
}