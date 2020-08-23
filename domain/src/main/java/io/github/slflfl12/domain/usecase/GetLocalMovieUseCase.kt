package io.github.slflfl12.domain.usecase

import io.github.slflfl12.domain.model.MovieModel
import io.github.slflfl12.domain.repository.MovieRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetLocalMovieUseCase(
    private val movieRepository: MovieRepository
): SingleUseCase<MovieModel, Int>() {

    override fun buildUseCaseSingle(params: Int): Single<MovieModel> =
        movieRepository.getLocalMovie(params)
            .subscribeOn(Schedulers.io())
}