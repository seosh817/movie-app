package io.github.slflfl12.data.local

import io.github.slflfl12.data.model.TvData
import io.reactivex.Completable
import io.reactivex.Single

interface TvLocalDataSource {

    fun insertTvList(tvDataList: List<TvData>): Completable

    fun updateTv(tv: TvData): Completable

    fun getTv(id: Int): Single<TvData>

    fun getLocalTvList(page: Int): Single<List<TvData>>
}