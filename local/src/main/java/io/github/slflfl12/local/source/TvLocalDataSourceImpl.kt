package io.github.slflfl12.local.source

import io.github.slflfl12.data.local.TvLocalDataSource
import io.github.slflfl12.data.model.TvData
import io.github.slflfl12.local.dao.TvDao
import io.github.slflfl12.local.mapper.TvLocalMapper
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class TvLocalDataSourceImpl(
    private val tvDao: TvDao
) : TvLocalDataSource {
    override fun insertTvList(tvDataList: List<TvData>): Completable {
        return tvDao.insertTvList(tvDataList.map(TvLocalMapper::mapToLocal))
            .subscribeOn(Schedulers.io())
    }

    override fun insertTv(tvData: TvData): Completable {
        return tvDao.insertTv(TvLocalMapper.mapToLocal(tvData))
    }

    override fun updateTv(tv: TvData): Completable {
        return tvDao.updateTv(TvLocalMapper.mapToLocal(tv))
            .subscribeOn(Schedulers.io())
    }

    override fun getTv(id: Int): Single<TvData> {
        return tvDao.getTv(id).map(TvLocalMapper::mapToData)
            .subscribeOn(Schedulers.io())
    }

    override fun getLocalTvList(page: Int): Single<List<TvData>> {
        return tvDao.getTvList(page).map { it.map(TvLocalMapper::mapToData)}
            .subscribeOn(Schedulers.io())
    }

    override fun getFavoriteTvList(): Single<List<TvData>> {
        return tvDao.getFavoriteTvList().map { it.map(TvLocalMapper::mapToData)}
            .subscribeOn(Schedulers.io())
    }
}