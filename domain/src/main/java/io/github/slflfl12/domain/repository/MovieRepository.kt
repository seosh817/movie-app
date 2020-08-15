package io.github.slflfl12.domain.repository

import io.github.slflfl12.domain.model.*
import io.reactivex.Completable
import io.reactivex.Single

interface MovieRepository {

    fun insertMovieList(movieDataList: List<MovieModel>): Completable

    fun updateMovie(movie: MovieModel): Completable

    fun getLocalMovie(id: Int): Single<MovieModel>

    fun getLocalMovieList(page: Int): Single<List<MovieModel>>


    fun getKeywordList(id: Int): Single<List<KeywordModel>>

    fun getVideoList(id: Int): Single<List<VideoModel>>

    fun getReviewList(id: Int): Single<List<ReviewModel>>

}