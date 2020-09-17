package io.github.slflfl12.domain.usecase

import android.util.Log
import io.github.slflfl12.domain.model.MovieModel
import io.github.slflfl12.domain.repository.MovieRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetMovieFavoriteUseCase(
    private val movieRepository: MovieRepository
) : SingleUseCase<MovieModel, Int>() {

    override fun buildUseCaseSingle(params: Int): Single<MovieModel> {
        return movieRepository.getLocalMovie(params).flatMapCompletable { movie ->
            movie.favorite = !movie.favorite
            movieRepository.updateMovie(movie)
        }.andThen(movieRepository.getLocalMovie(params))
    }
}