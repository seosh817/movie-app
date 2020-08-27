package io.github.slflfl12.data.repository

import io.github.slflfl12.data.local.TvLocalDataSource
import io.github.slflfl12.data.mapper.KeywordDataMapper
import io.github.slflfl12.data.mapper.ReviewDataMapper
import io.github.slflfl12.data.mapper.TvDataMapper
import io.github.slflfl12.data.mapper.VideoDataMapper
import io.github.slflfl12.data.model.TvData
import io.github.slflfl12.data.remote.TvRemoteDataSource
import io.github.slflfl12.domain.model.KeywordModel
import io.github.slflfl12.domain.model.ReviewModel
import io.github.slflfl12.domain.model.TvModel
import io.github.slflfl12.domain.model.VideoModel
import io.github.slflfl12.domain.repository.TvRepository
import io.reactivex.Completable
import io.reactivex.Single

class TvRepositoryImpl(
    private val tvLocalDataSource: TvLocalDataSource,
    private val tvRemoteDataSource: TvRemoteDataSource
) : TvRepository {


    override fun insertTvList(tvDataList: List<TvModel>): Completable {
        return tvLocalDataSource.insertTvList(tvDataList.map(TvDataMapper::mapToData))
    }

    override fun getLocalTvList(page: Int): Single<List<TvModel>> {
        return tvLocalDataSource.getLocalTvList(page).map{it.map(TvDataMapper::mapToDomain)}
    }

    override fun getTv(id: Int): Single<TvModel> {
        return tvLocalDataSource.getTv(id).map(TvDataMapper::mapToDomain)
    }

    override fun updateTv(tv: TvModel): Completable {
        return tvLocalDataSource.updateTv(TvDataMapper.mapToData(tv))
    }

    override fun getKeywordList(id: Int): Single<List<KeywordModel>> {
        return tvRemoteDataSource.getKeywords(id).map { it.map(KeywordDataMapper::mapToDomain)}
    }

    override fun getReviewList(id: Int): Single<List<ReviewModel>> {
        return tvRemoteDataSource.getReviews(id).map { it.map(ReviewDataMapper::mapToDomain)}
    }

    override fun getVideoList(id: Int): Single<List<VideoModel>> {
        return tvRemoteDataSource.getVideos(id).map { it.map(VideoDataMapper::mapToDomain)}
    }
}