package io.github.slflfl12.data.repository

import io.github.slflfl12.domain.repository.MovieRepository
import io.github.slflfl12.movieapp.data.remote.source.MovieRemoteDataSource
import io.github.slflfl12.remote.model.Keyword
import io.github.slflfl12.remote.model.Review
import io.github.slflfl12.remote.model.Video
import io.reactivex.Single

class MovieRepositoryImpl(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override fun getMovies(id: Int): Single<List<Keyword>> {
        return movieRemoteDataSource.getKeywords(id).map { it.keywords }
    }

    override fun getVideos(id: Int): Single<List<Video>> {
        return movieRemoteDataSource.getVideos(id).map { it.videos }
    }

    override fun getReviews(id: Int): Single<List<Review>> {
        return movieRemoteDataSource.getReviews(id).map { it.reviews }
    }


}