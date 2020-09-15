package io.github.slflfl12.domain.usecase

import io.github.slflfl12.domain.model.MovieModel
import io.github.slflfl12.domain.model.ReviewModel
import io.github.slflfl12.domain.repository.MovieRepository
import io.reactivex.Single

class GetMovieReviewListUseCase(
    private val movieRepository: MovieRepository
) : SingleUseCase<List<ReviewModel>, MovieModel>() {

    override fun buildUseCaseSingle(params: MovieModel): Single<List<ReviewModel>> =
        movieRepository.getLocalMovie(params.id).flatMap { movie ->
            if (movie.reviews.isNullOrEmpty()) {
                movieRepository.getReviewList(params.id).flatMapCompletable {
                    movie.reviews = it
                    movieRepository.updateMovie(movie)
                }.andThen(movieRepository.getReviewList(params.id))
            } else {
                movieRepository.getReviewList(params.id)
            }
        }.onErrorResumeNext {
            movieRepository.insertMovie(params).andThen(
                Single.just(params)
            ).flatMap { movie ->
                movieRepository.getReviewList(params.id).flatMapCompletable {
                    movie.reviews = it
                    movieRepository.updateMovie(movie)
                }.andThen(movieRepository.getReviewList(params.id))
            }
        }
}