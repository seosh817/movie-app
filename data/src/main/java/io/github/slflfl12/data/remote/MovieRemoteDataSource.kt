package io.github.slflfl12.data.remote

import io.github.slflfl12.data.model.KeywordData
import io.github.slflfl12.data.model.ReviewData
import io.github.slflfl12.data.model.VideoData
import io.reactivex.Single

interface MovieRemoteDataSource {

    fun getKeywords(id: Int): Single<List<KeywordData>>

    fun getVideos(id: Int): Single<List<VideoData>>

    fun getReviews(id: Int): Single<List<ReviewData>>
}