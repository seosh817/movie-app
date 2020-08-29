package io.github.slflfl12.domain.usecase

import io.github.slflfl12.domain.repository.MovieRepository
import io.reactivex.Completable

class LoadMovieKeywordListUseCase(
    private val movieRepository: MovieRepository
): CompletableUseCase<Int>() {
    override fun buildUseCaseCompletable(params: Int): Completable =
        movieRepository.getLocalMovie(params).flatMapCompletable { movie ->
            if(movie.keywords.isNullOrEmpty()) {
                movieRepository.getKeywordList(params).flatMapCompletable {
                    movie.keywords = it
                    movieRepository.updateMovie(movie)
                }
            } else {
                Completable.complete()
            }
        }
}