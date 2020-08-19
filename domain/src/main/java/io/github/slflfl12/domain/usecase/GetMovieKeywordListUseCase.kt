package io.github.slflfl12.domain.usecase

import io.github.slflfl12.domain.model.KeywordModel
import io.github.slflfl12.domain.repository.MovieRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetMovieKeywordListUseCase(
    private val movieRepository: MovieRepository
) : SingleUseCase<List<KeywordModel>, Int>(){

    override fun buildUseCaseSingle(params: Int): Single<List<KeywordModel>> =
        movieRepository.getKeywordList(params).subscribeOn(Schedulers.io())
}