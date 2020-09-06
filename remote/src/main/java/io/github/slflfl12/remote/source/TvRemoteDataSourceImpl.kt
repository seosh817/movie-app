package io.github.slflfl12.remote.source

import android.util.Log
import io.github.slflfl12.data.model.KeywordData
import io.github.slflfl12.data.model.ReviewData
import io.github.slflfl12.data.model.VideoData
import io.github.slflfl12.data.remote.TvRemoteDataSource
import io.github.slflfl12.remote.api.TvApiService
import io.github.slflfl12.remote.mapper.KeywordRemoteMapper
import io.github.slflfl12.remote.mapper.ReviewRemoteMapper
import io.github.slflfl12.remote.mapper.VideoRemoteMapper
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class TvRemoteDataSourceImpl(
    private val tvApiService: TvApiService
) : TvRemoteDataSource {
    override fun getKeywords(id: Int): Single<List<KeywordData>> {
        return tvApiService.getKeywords(id).map {
            it.keywords.map(KeywordRemoteMapper::mapToData)
        }.subscribeOn(Schedulers.io())
    }


    override fun getReviews(id: Int): Single<List<ReviewData>> {
        return tvApiService.getReviews(id).map {
            it.reviews.map(ReviewRemoteMapper::mapToData)
        }.subscribeOn(Schedulers.io())
    }

    override fun getVideos(id: Int): Single<List<VideoData>> {
        return tvApiService.getVideos(id).map {
            it.videos.map(VideoRemoteMapper::mapToData)
        }.subscribeOn(Schedulers.io())
    }
}