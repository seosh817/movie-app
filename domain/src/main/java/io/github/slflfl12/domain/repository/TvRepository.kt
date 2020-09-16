package io.github.slflfl12.domain.repository

import io.github.slflfl12.domain.model.KeywordModel
import io.github.slflfl12.domain.model.ReviewModel
import io.github.slflfl12.domain.model.TvModel
import io.github.slflfl12.domain.model.VideoModel
import io.reactivex.Completable
import io.reactivex.Single

interface TvRepository {

    fun insertTvList(tvModelList: List<TvModel>): Completable

    fun insertTv(tvModel: TvModel): Completable

    fun updateTv(tv: TvModel): Completable

    fun getLocalTv(id: Int): Single<TvModel>

    fun getLocalTvList(page: Int): Single<List<TvModel>>

    fun getKeywordList(id: Int): Single<List<KeywordModel>>

    fun getVideoList(id: Int): Single<List<VideoModel>>

    fun getReviewList(id: Int): Single<List<ReviewModel>>
}