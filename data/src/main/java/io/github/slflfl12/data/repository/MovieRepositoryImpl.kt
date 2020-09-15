package io.github.slflfl12.data.repository

import io.github.slflfl12.data.local.MovieLocalDataSource
import io.github.slflfl12.data.mapper.KeywordDataMapper
import io.github.slflfl12.data.mapper.MovieDataMapper
import io.github.slflfl12.data.mapper.ReviewDataMapper
import io.github.slflfl12.data.mapper.VideoDataMapper
import io.github.slflfl12.data.model.KeywordData
import io.github.slflfl12.data.model.MovieData
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
        return movieLocalDataSource.insertMovieList(movieDataList.map(MovieDataMapper::mapToData))
    }

    override fun insertMovie(movie: MovieModel): Completable {
        return movieLocalDataSource.insertMovie(MovieDataMapper.mapToData(movie))
    }

    override fun updateMovie(movie: MovieModel): Completable {
        return movieLocalDataSource.updateMovie(MovieDataMapper.mapToData(movie))
    }

    override fun getLocalMovie(id: Int): Single<MovieModel> {
        return movieLocalDataSource.getMovie(id).map(MovieDataMapper::mapToDomain)
    }

    override fun getLocalMovieList(page: Int): Single<List<MovieModel>> {
        return movieLocalDataSource.getLocalMovieList(page)
            .map { it.map(MovieDataMapper::mapToDomain) }
    }

    override fun getKeywordList(id: Int): Single<List<KeywordModel>> {
        return movieRemoteDataSource.getKeywords(id).map { it.map(KeywordDataMapper::mapToDomain) }
    }

    override fun getVideoList(id: Int): Single<List<VideoModel>> {
        return movieRemoteDataSource.getVideos(id).map { it.map(VideoDataMapper::mapToDomain) }
    }

    override fun getReviewList(id: Int): Single<List<ReviewModel>> {
        return movieRemoteDataSource.getReviews(id).map { it.map(ReviewDataMapper::mapToDomain) }
    }


}