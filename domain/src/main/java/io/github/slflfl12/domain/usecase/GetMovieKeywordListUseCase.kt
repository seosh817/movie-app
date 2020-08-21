package io.github.slflfl12.domain.usecase

import io.github.slflfl12.domain.model.KeywordModel
import io.github.slflfl12.domain.repository.MovieRepository
import io.reactivex.Single

class GetMovieKeywordListUseCase(
    private val movieRepository: MovieRepository
) : SingleUseCase<List<KeywordModel>, Int>() {

    override fun buildUseCaseSingle(params: Int): Single<List<KeywordModel>> =
        movieRepository.getLocalMovie(params).flatMap {movie ->
            if(movie.keywords.isEmpty()) {
                movieRepository.getKeywordList(params).flatMapCompletable {
                    movie.keywords = it
                    movieRepository.updateMovie(movie)
                }.andThen(movieRepository.getKeywordList(params))
            }
            else {
                movieRepository.getKeywordList(params)
            }
        }

}
/*

if(movie.keywords.isEmpty()) {
    movieRepository.getKeywordList(params).flatMap {
        var updateMovie = movie
        updateMovie.keywords = it
        movieRepository.updateMovie(updateMovie).andThen(
            Single.just(it)
        )
    }*/
