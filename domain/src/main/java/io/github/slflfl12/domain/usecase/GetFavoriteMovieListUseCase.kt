package io.github.slflfl12.domain.usecase

import io.github.slflfl12.domain.model.MovieModel
import io.github.slflfl12.domain.repository.MovieRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetFavoriteMovieListUseCase(
    private val movieRepository: MovieRepository
): SingleUseCase<List<MovieModel>, Unit>() {

    override fun buildUseCaseSingle(params: Unit): Single<List<MovieModel>> {
        return movieRepository.getFavoriteMovieList()
            .subscribeOn(Schedulers.io())
    }
}