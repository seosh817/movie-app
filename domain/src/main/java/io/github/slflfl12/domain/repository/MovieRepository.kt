package io.github.slflfl12.domain.repository

import io.github.slflfl12.remote.model.Keyword
import io.github.slflfl12.remote.model.Review
import io.github.slflfl12.remote.model.Video
import io.reactivex.Single

interface MovieRepository {

    fun getMovies(id: Int): Single<List<Keyword>>

    fun getVideos(id: Int): Single<List<Video>>

    fun getReviews(id: Int): Single<List<Review>>
}