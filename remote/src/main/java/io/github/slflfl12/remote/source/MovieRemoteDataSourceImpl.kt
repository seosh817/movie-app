package io.github.slflfl12.remote.source

import io.github.slflfl12.data.remote.MovieRemoteDataSource
import io.github.slflfl12.remote.model.KeywordResponse
import io.github.slflfl12.remote.model.ReviewResponse
import io.github.slflfl12.remote.model.VideoResponse
import io.reactivex.Single

class MovieRemoteDataSourceImpl(private val movieApiService: io.github.slflfl12.remote.api.MovieApiService) :
    MovieRemoteDataSource {

    override fun getKeywords(id: Int): Single<KeywordResponse> {
        return movieApiService.getKeywords(id)
    }

    override fun getVideos(id: Int): Single<VideoResponse> {
        return movieApiService.getVideos(id)
    }

    override fun getReviews(id: Int): Single<ReviewResponse> {
        return movieApiService.getReviews(id)
    }
}