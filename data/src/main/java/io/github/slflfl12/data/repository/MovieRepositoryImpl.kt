package io.github.slflfl12.data.repository

import io.github.slflfl12.data.local.MovieLocalDataSource
import io.github.slflfl12.data.model.KeywordData
import io.github.slflfl12.data.model.ReviewData
import io.github.slflfl12.data.model.VideoData
import io.github.slflfl12.data.remote.MovieRemoteDataSource
import io.github.slflfl12.domain.model.KeywordModel
import io.github.slflfl12.domain.model.MovieModel
import io.github.slflfl12.domain.model.ReviewModel
import io.github.slflfl12.domain.model.VideoModel
import io.github.slflfl12.domain.repository.MovieRepository
import io.reactivex.Completable
import io.reactivex.Single

class MovieRepositoryImpl(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {
    override fun insertMovieList(movieDataList: List<MovieModel>): Completable {
        TODO("Not yet implemented")
    }

    override fun updateMovie(movie: MovieModel): Completable {
        TODO("Not yet implemented")
    }

    override fun getMovie(id: Int): Single<MovieModel> {
        TODO("Not yet implemented")
    }

    override fun getLocalMovieList(page: Int): Single<List<MovieModel>> {
        TODO("Not yet implemented")
    }

    override fun getKeywordList(id: Int): Single<List<KeywordModel>> {
        TODO("Not yet implemented")
    }

    override fun getVideoList(id: Int): Single<List<VideoModel>> {
        TODO("Not yet implemented")
    }

    override fun getReviewList(id: Int): Single<List<ReviewModel>> {
        TODO("Not yet implemented")
    }


}