package io.github.slflfl12.domain.usecase

import io.github.slflfl12.domain.model.VideoModel
import io.github.slflfl12.domain.repository.MovieRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetMovieVideoListUseCase(
    private val movieRepository: MovieRepository
): SingleUseCase<List<VideoModel>, Int>() {

    override fun buildUseCaseSingle(params: Int): Single<List<VideoModel>> =
        movieRepository.getVideoList(params)
            .subscribeOn(Schedulers.io())
}