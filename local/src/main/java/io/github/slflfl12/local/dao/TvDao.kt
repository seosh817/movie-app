package io.github.slflfl12.local.dao

import androidx.room.*
import io.github.slflfl12.local.model.MovieEntity
import io.github.slflfl12.local.model.TvEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface TvDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvList(tvs: List<TvEntity>?): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(tv: TvEntity): Completable

    @Update
    fun updateTv(tv: TvEntity): Completable

    @Query("SELECT * FROM tv")
    fun getAllTvs(): Single<TvEntity>

    @Query("SELECT * FROM tv WHERE id = :id")
    fun getTv(id: Int): Single<TvEntity>

    @Query("SELECT * FROM tv WHERE page = :page")
    fun getTvList(page: Int): Single<List<TvEntity>>

    @Query("SELECT * FROM tv WHERE favorite = '1'")
    fun getFavoriteTvList(): Single<List<TvEntity>>
}