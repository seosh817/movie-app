package io.github.slflfl12.domain.usecase

import android.util.Log
import io.github.slflfl12.domain.model.KeywordModel
import io.github.slflfl12.domain.repository.MovieRepository
import io.reactivex.Single

class GetMovieKeywordListUseCase(
    private val movieRepository: MovieRepository
) : SingleUseCase<List<KeywordModel>, Int>() {

    override fun buildUseCaseSingle(params: Int): Single<List<KeywordModel>> =
        movieRepository.getLocalMovie(params).flatMap {movie ->
            if(movie.keywords.isNullOrEmpty()) {
                movieRepository.getKeywordList(params).flatMapCompletable {
                    movie.keywords = it
                    movieRepository.updateMovie(movie)
                }.andThen(movieRepository.getKeywordList(params))
            }
            else {
                Single.just(movie.keywords)
            }
        }

}

