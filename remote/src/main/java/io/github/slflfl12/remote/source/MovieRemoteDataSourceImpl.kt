package io.github.slflfl12.remote.source

import io.github.slflfl12.data.model.KeywordData
import io.github.slflfl12.data.model.ReviewData
import io.github.slflfl12.data.model.VideoData
import io.github.slflfl12.data.remote.MovieRemoteDataSource
import io.github.slflfl12.remote.api.MovieApiService
import io.github.slflfl12.remote.mapper.KeywordRemoteMapper
import io.github.slflfl12.remote.mapper.ReviewRemoteMapper
import io.github.slflfl12.remote.mapper.VideoRemoteMapper
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class MovieRemoteDataSourceImpl(private val movieApiService: MovieApiService) :
    MovieRemoteDataSource {

    override fun getKeywords(id: Int): Single<List<KeywordData>> {
        return movieApiService.getKeywords(id).map { it.keywords.map(KeywordRemoteMapper::mapToData) }
            .subscribeOn(Schedulers.io())

    }

    override fun getVideos(id: Int): Single<List<VideoData>> {
        return movieApiService.getVideos(id).map { it.videos.map(VideoRemoteMapper::mapToData) }
            .subscribeOn(Schedulers.io())
    }

    override fun getReviews(id: Int): Single<List<ReviewData>> {
        return movieApiService.getReviews(id).map { it.reviews.map(ReviewRemoteMapper::mapToData) }
            .subscribeOn(Schedulers.io())

    }


}