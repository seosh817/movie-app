package io.github.slflfl12.domain.usecase

import io.github.slflfl12.domain.model.MovieModel
import io.github.slflfl12.domain.model.VideoModel
import io.github.slflfl12.domain.repository.MovieRepository
import io.reactivex.Single

class GetMovieVideoListUseCase(
    private val movieRepository: MovieRepository
) : SingleUseCase<List<VideoModel>, MovieModel>() {

    override fun buildUseCaseSingle(params: MovieModel): Single<List<VideoModel>> =
        movieRepository.getLocalMovie(params.id).flatMap { movie ->
            if (movie.videos.isNullOrEmpty()) {
                movieRepository.getVideoList(params.id).flatMapCompletable {
                    movie.videos = it
                    movieRepository.updateMovie(movie)
                }.andThen(movieRepository.getVideoList(params.id))
            } else {
                movieRepository.getVideoList(params.id)
            }
        }.onErrorResumeNext {
            movieRepository.insertMovie(params).andThen(
                Single.just(params)
            ).flatMap { movie ->
                movieRepository.getVideoList(params.id).flatMapCompletable {
                    movie.videos = it
                    movieRepository.updateMovie(movie)
                }.andThen(movieRepository.getVideoList(params.id))
            }
        }
}