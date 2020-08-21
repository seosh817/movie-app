package io.github.slflfl12.domain.usecase

import io.github.slflfl12.domain.model.MovieModel
import io.github.slflfl12.domain.repository.MovieRepository
import io.reactivex.Completable

class InsertMoviesUseCase(
    val movieRepository: MovieRepository
) : CompletableUseCase<List<MovieModel>>() {
    override fun buildUseCaseCompletable(params: List<MovieModel>): Completable {
        return movieRepository.insertMovieList(params)
    }

}