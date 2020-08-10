package io.github.slflfl12.remote.api

import io.github.slflfl12.remote.model.KeywordResponse
import io.github.slflfl12.remote.model.ReviewResponse
import io.github.slflfl12.remote.model.VideoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {

    @GET("/3/movie/{movie_id}/keywords")
    fun getKeywords(@Path("movie_id")id: Int): Single<KeywordResponse>

    @GET("/3/movie/{movie_id}/videos")
    fun getVideos(@Path("movie_id")id: Int): Single<VideoResponse>

    @GET("/3/movie/{movie_id}/reviews")
    fun getReviews(@Path("movie_id")id: Int): Single<ReviewResponse>
}