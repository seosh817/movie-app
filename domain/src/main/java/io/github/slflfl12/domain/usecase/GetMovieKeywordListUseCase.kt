package io.github.slflfl12.domain.usecase

import io.github.slflfl12.domain.model.KeywordModel
import io.github.slflfl12.domain.model.MovieModel
import io.github.slflfl12.domain.repository.MovieRepository
import io.reactivex.Single

class GetMovieKeywordListUseCase(
    private val movieRepository: MovieRepository
) : SingleUseCase<List<KeywordModel>, MovieModel>() {

    override fun buildUseCaseSingle(params: MovieModel): Single<List<KeywordModel>> {
        return movieRepository.getLocalMovie(params.id).flatMap { movie ->
            if (movie.keywords.isNullOrEmpty()) {
                movieRepository.getKeywordList(params.id).flatMapCompletable {
                    movie.keywords = it
                    movieRepository.updateMovie(movie)
                }.andThen(movieRepository.getKeywordList(params.id))
            } else {
                movieRepository.getKeywordList(params.id)
            }
        }.onErrorResumeNext {
            movieRepository.insertMovie(params).andThen(
                Single.just(params)
            ).flatMap { movie ->
                movieRepository.getKeywordList(params.id).flatMapCompletable {
                    movie.keywords = it
                    movieRepository.updateMovie(movie)
                }.andThen(movieRepository.getKeywordList(params.id))
            }
        }
    }
}

