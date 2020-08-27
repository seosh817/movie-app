package io.github.slflfl12.remote.api

import io.github.slflfl12.remote.model.KeywordResponse
import io.github.slflfl12.remote.model.ReviewResponse
import io.github.slflfl12.remote.model.VideoResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TvApiService {

    @GET("/3/tv/{id}/keywords")
    fun getKeywords(@Path("id") id: Int): Single<KeywordResponse>

    @GET("/3/tv/{id}/videos")
    fun getVideos(@Path("id") id: Int): Single<VideoResponse>

    @GET("/3/tv/{id}/reviews")
    fun getReviews(@Path("id") id: Int): Single<ReviewResponse>
}