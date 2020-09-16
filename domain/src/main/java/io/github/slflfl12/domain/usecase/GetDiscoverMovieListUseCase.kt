package io.github.slflfl12.domain.usecase

import android.util.Log
import io.github.slflfl12.domain.model.MovieModel
import io.github.slflfl12.domain.repository.DiscoverRepository
import io.github.slflfl12.domain.repository.MovieRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetDiscoverMovieListUseCase(
    private val movieRepository: MovieRepository,
    private val discoverRepository: DiscoverRepository
) : SingleUseCase<List<MovieModel>, Int>() {

    override fun buildUseCaseSingle(params: Int): Single<List<MovieModel>> {
        return discoverRepository.getDiscoverMovies(params).flatMap { remote ->
            movieRepository.insertMovieList(remote).andThen(
                Single.just(remote)
            )
        }.onErrorResumeNext {
            movieRepository.getLocalMovieList(params)
        }.subscribeOn(Schedulers.io())
    }
}