package io.github.slflfl12.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.github.slflfl12.local.model.MovieEntity
import io.github.slflfl12.local.model.TvEntity
import io.reactivex.Completable
import io.reactivex.Single

interface TvDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvList(tvs: List<TvEntity>?): Completable

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