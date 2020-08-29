package io.github.slflfl12.domain.usecase

import io.github.slflfl12.domain.model.KeywordModel
import io.github.slflfl12.domain.model.ReviewModel
import io.github.slflfl12.domain.repository.MovieRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetMovieReviewListUseCase(
    private val movieRepository: MovieRepository
): SingleUseCase<List<ReviewModel>, Int>() {

    override fun buildUseCaseSingle(params: Int): Single<List<ReviewModel>> =
        movieRepository.getLocalMovie(params).flatMap {movie ->
            if(movie.reviews.isNullOrEmpty()) {
                movieRepository.getReviewList(params).flatMapCompletable {
                    movie.reviews = it
                    movieRepository.updateMovie(movie)
                }.andThen(movieRepository.getReviewList(params))
            }
            else {
                Single.just(movie.reviews)
            }
        }
}