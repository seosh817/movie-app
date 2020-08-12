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
    fun updateMovie(movie: MovieEntity)

    @Query("SELECT * FROM MovieEntity WHERE id = :_id")
    fun getMovie(_id: Int): Single<MovieEntity>

    @Query("SELECT * FROM MovieEntity WHERE page = :_page")
    fun getMovieList(_page: Int): Single<List<MovieEntity>>

    @Query("SELECT * FROM MovieEntity WHERE favorite = '1'")
    fun getFavoriteMovieList(): Single<List<MovieEntity>>
}