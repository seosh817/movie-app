package io.github.slflfl12.data.remote

import io.github.slflfl12.remote.model.KeywordResponse
import io.github.slflfl12.remote.model.ReviewResponse
import io.github.slflfl12.remote.model.VideoResponse
import io.reactivex.Single

interface MovieRemoteDataSource {

    fun getKeywords(id: Int): Single<KeywordResponse>

    fun getVideos(id: Int): Single<VideoResponse>

    fun getReviews(id: Int): Single<ReviewResponse>
}