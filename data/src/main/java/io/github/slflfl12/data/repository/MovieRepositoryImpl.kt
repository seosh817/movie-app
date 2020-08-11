package io.github.slflfl12.data.repository

import io.github.slflfl12.data.model.KeywordData
import io.github.slflfl12.data.model.ReviewData
import io.github.slflfl12.data.model.VideoData
import io.github.slflfl12.data.remote.MovieRemoteDataSource
import io.github.slflfl12.domain.repository.MovieRepository
import io.reactivex.Single

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override fun getMovies(id: Int): Single<List<KeywordData>> {
        return movieRemoteDataSource.getKeywords(id)
    }

    override fun getVideos(id: Int): Single<List<VideoData>> {
        return movieRemoteDataSource.getVideos(id)
    }

    override fun getReviews(id: Int): Single<List<ReviewData>> {
        return movieRemoteDataSource.getReviews(id)
    }


}